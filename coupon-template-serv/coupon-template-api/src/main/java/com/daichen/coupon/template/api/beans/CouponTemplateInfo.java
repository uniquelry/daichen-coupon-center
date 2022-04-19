package com.daichen.coupon.template.api.beans;

import com.daichen.coupon.template.api.beans.rules.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:43
 * @description 创建优惠券模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateInfo {

    private Long id;
    /**
     * 优惠券名称
     */
    @NotNull
    private String name;
    /**
     * 优惠券详细信息
     */
    @NotNull
    private String description;
    /**
     * 优惠券类型
     */
    @NotNull
    private String type;
    /**
     * 优惠券适用的门店，如果是空则代表所有门店适用
     */
    private Long shopId;
    /**
     * 详细的使用规则
     */
    @NotNull
    private TemplateRule rule;
    /**
     * 优惠券可用状态
     */
    private Boolean available;
}
