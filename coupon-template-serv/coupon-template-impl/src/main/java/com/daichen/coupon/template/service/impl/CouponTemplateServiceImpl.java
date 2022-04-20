package com.daichen.coupon.template.service.impl;

import com.daichen.coupon.template.api.beans.CouponTemplateInfo;
import com.daichen.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.daichen.coupon.template.api.beans.TemplateSearchParams;
import com.daichen.coupon.template.api.enums.CouponType;
import com.daichen.coupon.template.converter.CouponTemplateConverter;
import com.daichen.coupon.template.dao.CouponTemplateDao;
import com.daichen.coupon.template.dao.entity.CouponTemplate;
import com.daichen.coupon.template.service.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 22:56
 * @description 优惠券模板类相关操作
 */
@Slf4j
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

    /**
     * 单个门店最多可以创建 100 张优惠券模板
     */
    private final static Integer MAXIMUM_NUMBER_OF_TEMPLATES_PER_SHOP = 100;

    @Autowired
    private CouponTemplateDao couponTemplateDao;

    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        if (request.getShopId() != null) {
            Integer count = couponTemplateDao.countByShopIdAndAvailable(request.getShopId(), true);
            if (count >= MAXIMUM_NUMBER_OF_TEMPLATES_PER_SHOP) {
                log.error("the totals of coupon template exceeds maximum number");
                throw new UnsupportedOperationException("exceeded the maximum of coupon templates that you can create");
            }
        }

        // 创建优惠券
        CouponTemplate template = CouponTemplate.builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(CouponType.convert(request.getType()))
                .shopId(request.getShopId())
                .rule(request.getRule())
                .available(true)
                .build();
        template = couponTemplateDao.save(template);

        return CouponTemplateConverter.convertToTemplateInfo(template);
    }

    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        CouponTemplate source = couponTemplateDao.findById(templateId)
                .orElseThrow(() -> new IllegalArgumentException("invalid template Id"));

        CouponTemplate target = new CouponTemplate();
        BeanUtils.copyProperties(source, target);
        target.setId(null);
        target.setAvailable(true);
        couponTemplateDao.save(target);

        return CouponTemplateConverter.convertToTemplateInfo(target);
    }

    @Override
    public void deleteTemplate(Long id) {
        int rows = couponTemplateDao.updateCouponUnavailable(id);
        if (rows == 0) {
            throw new IllegalArgumentException("Template Not Found: " + id);
        }
    }

    @Override
    public CouponTemplateInfo getTemplateInfo(Long id) {
        Optional<CouponTemplate> template = couponTemplateDao.findById(id);
        return template.map(CouponTemplateConverter::convertToTemplateInfo).orElse(null);
    }

    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        List<CouponTemplate> templates = couponTemplateDao.findAllById(ids);

        return templates.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
    }

    @Override
    public PagedCouponTemplateInfo searchTemplateInfo(TemplateSearchParams request) {
        CouponTemplate example = CouponTemplate.builder()
                .name(request.getName())
                .type(CouponType.convert(request.getType()))
                .shopId(request.getShopId())
                .available(request.getAvailable())
                .build();

        Pageable page = PageRequest.of(request.getPage(), request.getPageSize());
        Page<CouponTemplate> result = couponTemplateDao.findAll(Example.of(example), page);
        List<CouponTemplateInfo> couponTemplateInfos = result.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toList());

        return PagedCouponTemplateInfo.builder()
                .templates(couponTemplateInfos)
                .page(request.getPage())
                .total(result.getTotalElements())
                .build();
    }
}
