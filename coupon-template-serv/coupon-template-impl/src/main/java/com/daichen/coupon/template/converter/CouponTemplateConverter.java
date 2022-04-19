package com.daichen.coupon.template.converter;

import com.daichen.coupon.template.api.beans.CouponTemplateInfo;
import com.daichen.coupon.template.dao.entity.CouponTemplate;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 23:10
 * @description
 */
public class CouponTemplateConverter {

    public static CouponTemplateInfo convertToTemplateInfo(CouponTemplate template) {
        return CouponTemplateInfo.builder()
                .id(template.getId())
                .name(template.getName())
                .description(template.getDescription())
                .type(template.getType().getCode())
                .shopId(template.getShopId())
                .rule(template.getRule())
                .available(template.getAvailable())
                .build();
    }
}
