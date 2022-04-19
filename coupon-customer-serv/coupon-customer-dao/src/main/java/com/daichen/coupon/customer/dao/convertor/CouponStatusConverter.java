package com.daichen.coupon.customer.dao.convertor;

import com.daichen.coupon.customer.api.enums.CouponStatus;

import javax.persistence.AttributeConverter;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:35
 * @description 如果需要把 DB 里的值转换成 enum 对象，就采用这种方式就好了。利用泛型模板继承 AttributeConverter
 */
public class CouponStatusConverter implements AttributeConverter<CouponStatus, Integer> {

    /**
     * enum 转 DB value
     *
     * @param status
     * @return
     */
    @Override
    public Integer convertToDatabaseColumn(CouponStatus status) {
        return status.getCode();
    }

    /**
     * DB value 转 enum 值
     *
     * @param code
     * @return
     */
    @Override
    public CouponStatus convertToEntityAttribute(Integer code) {
        return CouponStatus.convert(code);
    }
}
