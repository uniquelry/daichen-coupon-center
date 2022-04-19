package com.daichen.coupon.customer.converter;

import com.daichen.coupon.customer.dao.entity.Coupon;
import com.daichen.coupon.template.api.beans.CouponInfo;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:43
 * @description
 */
public class CouponConverter {

    public static CouponInfo convertToCoupon(Coupon coupon) {
        return CouponInfo.builder()
                .id(coupon.getId())
                .templateId(coupon.getTemplateId())
                .userId(coupon.getUserId())
                .shopId(coupon.getShopId())
                .status(coupon.getStatus().getCode())
                .template(coupon.getTemplateInfo())
                .build();
    }
}
