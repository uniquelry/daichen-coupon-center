package com.daichen.coupon.template.dao.converter;

import com.daichen.coupon.template.api.enums.CouponType;

import javax.persistence.AttributeConverter;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 19:00
 * @description
 */
public class CouponTypeConverter implements AttributeConverter<CouponType, String> {

    @Override
    public String convertToDatabaseColumn(CouponType couponCategory) {
        return couponCategory.getCode();
    }

    @Override
    public CouponType convertToEntityAttribute(String code) {
        return CouponType.convert(code);
    }
}
