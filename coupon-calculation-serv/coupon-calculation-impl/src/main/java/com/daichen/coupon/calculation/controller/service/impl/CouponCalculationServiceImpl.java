package com.daichen.coupon.calculation.controller.service.impl;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.api.beans.SimulationOrder;
import com.daichen.coupon.calculation.api.beans.SimulationResponse;
import com.daichen.coupon.calculation.controller.service.CouponCalculationService;
import com.daichen.coupon.calculation.template.CouponTemplateFactory;
import com.daichen.coupon.template.api.beans.CouponInfo;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:21
 * @description
 */
@Slf4j
@Service
public class CouponCalculationServiceImpl implements CouponCalculationService {

    @Autowired
    private CouponTemplateFactory couponProcessorFactory;

    @Override
    public ShoppingCart calculateOrder(ShoppingCart cart) {
        log.info("calculate order cart: {}", new Gson().toJson(cart));
        return couponProcessorFactory.getTemplate(cart).calculate(cart);
    }

    @Override
    public SimulationResponse simulateOrder(SimulationOrder order) {
        SimulationResponse response = new SimulationResponse();
        long minOrderPrice = Long.MAX_VALUE;

        // 计算每一个优惠券的订单价格
        for (CouponInfo coupon : order.getCouponInfos()) {
            ShoppingCart cart = new ShoppingCart();
            cart.setProducts(order.getProducts());
            cart.setCouponInfos(Lists.newArrayList(coupon));
            cart = couponProcessorFactory.getTemplate(cart).calculate(cart);

            Long couponId = coupon.getId();
            Long orderPrice = cart.getCost();

            // 设置当前优惠券对应的订单价格
            response.getCouponToOrderPrice().put(couponId, orderPrice);

            // 比较订单价格，设置当前最优优惠券的 Id
            if (minOrderPrice > orderPrice) {
                response.setBestCouponId(coupon.getId());
                minOrderPrice = orderPrice;
            }
        }
        return response;
    }
}
