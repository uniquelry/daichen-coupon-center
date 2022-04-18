package com.daichen.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:27
 * @description 优惠券计算规则
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {

    /** 可以享受的折扣 */
    private Discount discount;
    /**
     * 每个人最多可以领券数量
     */
    private Integer limitation;
    /**
     * 过期时间
     */
    private Long deadline;
}
