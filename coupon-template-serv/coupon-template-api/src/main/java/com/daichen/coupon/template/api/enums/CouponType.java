package com.daichen.coupon.template.api.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:26
 * @description 优惠券类型
 */
@Getter
@AllArgsConstructor
public enum CouponType {

    /**
     * Unknown 类型的券，它专门用来对付故意输错 code 的恶意请求
     */
    UNKNOWN("unknown", "0"),
    MONEY_OFF("满减券", "1"),
    DISCOUNT("打折", "2"),
    RANDOM_DISCOUNT("随机减", "3"),
    LONELY_NIGHT_MONEY_OFF("晚间双倍优惠券", "4"),
    ;

    /**
     * 券类型描述
     */
    private final String description;
    /**
     * 存在数据库里的最终 code
     */
    private final String code;

    public static CouponType convert(String code) {
        return Stream.of(values()).filter(bean -> bean.code.equalsIgnoreCase(code))
                .findFirst().orElse(UNKNOWN);
    }
}
