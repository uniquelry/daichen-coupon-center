package com.daichen.coupon.customer.service.impl;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.api.beans.SimulationOrder;
import com.daichen.coupon.calculation.api.beans.SimulationResponse;
import com.daichen.coupon.calculation.controller.service.CouponCalculationService;
import com.daichen.coupon.customer.api.beans.RequestCoupon;
import com.daichen.coupon.customer.api.beans.SearchCoupon;
import com.daichen.coupon.customer.api.enums.CouponStatus;
import com.daichen.coupon.customer.converter.CouponConverter;
import com.daichen.coupon.customer.dao.CouponDao;
import com.daichen.coupon.customer.dao.entity.Coupon;
import com.daichen.coupon.customer.service.CouponCustomerService;
import com.daichen.coupon.template.api.beans.CouponInfo;
import com.daichen.coupon.template.api.beans.CouponTemplateInfo;
import com.daichen.coupon.template.service.CouponTemplateService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:46
 * @description
 */
@Slf4j
@Service
public class CouponCustomerServiceImpl implements CouponCustomerService {

    @Autowired
    private CouponDao couponDao;
    @Autowired
    private CouponTemplateService couponTemplateService;
    @Autowired
    private CouponCalculationService couponCalculationService;

    @Override
    public Coupon requestCoupon(RequestCoupon request) {
        Long couponTemplateId = request.getCouponTemplateId();
        CouponTemplateInfo templateInfo = couponTemplateService.getTemplateInfo(couponTemplateId);

        // 模板不存在则报错
        if (templateInfo == null) {
            log.error("invalid template id={}", couponTemplateId);
            throw new IllegalArgumentException("Invalid template id");
        }

        // 模板不能过期
        long now = Calendar.getInstance().getTimeInMillis();
        Long expTime = templateInfo.getRule().getDeadline();
        boolean expired = expTime != null && now >= expTime;
        if (expired || BooleanUtils.isFalse(templateInfo.getAvailable())) {
            log.error("template is not available id={}", couponTemplateId);
            throw new IllegalArgumentException("template is unavailable");
        }

        // 用户领券数量超过上限
        long count = couponDao.countByUserIdAndTemplateId(request.getUserId(), couponTemplateId);
        if (count >= templateInfo.getRule().getLimitation()) {
            log.error("exceeds maximum number");
            throw new IllegalArgumentException("exceeds maximum number");
        }

        Coupon coupon = Coupon.builder()
                .templateId(couponTemplateId)
                .userId(request.getUserId())
                .shopId(templateInfo.getShopId())
                .status(CouponStatus.AVAILABLE)
                .build();
        couponDao.save(coupon);
        return coupon;
    }

    @Override
    public ShoppingCart placeOrder(ShoppingCart cart) {
        if (CollectionUtils.isEmpty(cart.getProducts())) {
            log.error("invalid check out request, cart={}", cart);
            throw new IllegalArgumentException("cart if empty");
        }

        Coupon coupon = null;
        // 目前只支持单张优惠券
        Long couponId = Optional.ofNullable(cart.getCouponIds()).map(e -> e.get(0)).orElse(null);
        if (couponId != null) {
            // 如果有优惠券，验证是否可用，并且是当前客户的
            Coupon example = Coupon.builder()
                    .id(couponId)
                    .userId(cart.getUserId())
                    .status(CouponStatus.AVAILABLE)
                    .build();
            coupon = couponDao.findAll(Example.of(example))
                    .stream()
                    .findFirst()
                    // 如果找不到券，就抛出异常
                    .orElseThrow(() -> new RuntimeException("Coupon not found"));

            CouponInfo couponInfo = CouponConverter.convertToCoupon(coupon);
            couponInfo.setTemplate(couponTemplateService.getTemplateInfo(coupon.getTemplateId()));
            cart.setCouponInfos(Lists.newArrayList(couponInfo));
        }

        // 订单优惠券结算
        ShoppingCart checkoutCart = couponCalculationService.calculateOrder(cart);

        if (coupon != null) {
            // 如果优惠券没有被结算掉，而用户传递了优惠券，报错提示该订单满足不了优惠条件
            if (CollectionUtils.isEmpty(checkoutCart.getCouponInfos())) {
                log.error("cannot apply coupon to cart, couponId={}", coupon.getId());
                throw new IllegalArgumentException("coupon is not applicable to this cart");
            }

            log.info("update coupon status to used, couponId={}", coupon.getId());
            coupon.setStatus(CouponStatus.USED);
            couponDao.save(coupon);
        }

        return checkoutCart;
    }

    @Override
    public SimulationResponse simulateOrder(SimulationOrder order) {
        List<CouponInfo> couponInfos = Lists.newArrayList();
        // 挨个循环，把优惠券信息加载出来
        // 高并发场景下不能这么一个个循环，更好的做法是批量查询
        // 而且券模板一旦创建不会改内容，所以在创建端做数据异构放到缓存里，使用端从缓存捞 template 信息
        for (Long couponId : order.getCouponIds()) {
            Coupon example = Coupon.builder()
                    .userId(order.getUserId())
                    .id(couponId)
                    .status(CouponStatus.AVAILABLE)
                    .build();
            Optional<Coupon> couponOptional = couponDao.findAll(Example.of(example))
                    .stream()
                    .findFirst();
            // 加载优惠券模板信息
            if (couponOptional.isPresent()) {
                Coupon coupon = couponOptional.get();
                CouponInfo couponInfo = CouponConverter.convertToCoupon(coupon);
                couponInfo.setTemplate(couponTemplateService.getTemplateInfo(coupon.getTemplateId()));
                couponInfos.add(couponInfo);
            }
        }
        order.setCouponInfos(couponInfos);

        // 调用订单优惠券试算
        return couponCalculationService.simulateOrder(order);
    }

    @Override
    public void deleteCoupon(Long userId, Long couponId) {
        Coupon example = Coupon.builder()
                .id(couponId)
                .userId(userId)
                .status(CouponStatus.AVAILABLE)
                .build();
        Coupon coupon = couponDao.findAll(Example.of(example))
                .stream()
                .findFirst()
                // 如果找不到券，就抛出异常
                .orElseThrow(() -> new RuntimeException("Could not find available coupon"));

        coupon.setStatus(CouponStatus.INACTIVE);
        couponDao.save(coupon);
    }

    @Override
    public List<CouponInfo> findCoupon(SearchCoupon request) {
        // 在真正的生产环境，这个接口需要做分页查询，并且查询条件要封装成一个类
        Coupon example = Coupon.builder()
                .userId(request.getUserId())
                .shopId(request.getShopId())
                .status(CouponStatus.convert(request.getCouponStatus()))
                .build();

        // 这里你可以尝试实现分页查询
        List<Coupon> coupons = couponDao.findAll(Example.of(example));
        if (coupons.isEmpty()) {
            return Lists.newArrayList();
        }

        List<Long> templateIds = coupons.stream()
                .map(Coupon::getTemplateId)
                .collect(Collectors.toList());
        Map<Long, CouponTemplateInfo> templateMap = couponTemplateService.getTemplateInfoMap(templateIds);
        coupons.forEach(e -> e.setTemplateInfo(templateMap.get(e.getTemplateId())));

        return coupons.stream()
                .map(CouponConverter::convertToCoupon)
                .collect(Collectors.toList());
    }
}
