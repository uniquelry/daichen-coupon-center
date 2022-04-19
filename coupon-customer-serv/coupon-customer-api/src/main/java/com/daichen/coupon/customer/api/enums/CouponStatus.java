package com.daichen.coupon.customer.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:31
 * @description 优惠券的状态
 */
@Getter
@AllArgsConstructor
public enum CouponStatus {

    /**
     * 券状态
     */
    AVAILABLE("未使用", 1),
    USED("已用", 2),
    INACTIVE("已经注销", 3),
    ;

    /**
     * 描述
     */
    private final String desc;
    /**
     * 对应保存的编码
     */
    private final Integer code;

    public static CouponStatus convert(Integer code) {
        if (code == null) {
            return null;
        }
        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElse(null);
    }
}
