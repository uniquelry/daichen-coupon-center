package com.daichen.coupon.template.api.beans;

import com.daichen.coupon.template.api.beans.rules.TemplateRule;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 优惠券描述
     */
    @NotNull
    private String desc;
    /**
     * 优惠券类型
     */
    @NotNull
    private String type;
    /**
     * 适用门店 - 若无则为全店通用券
     */
    private Long shopId;
    /**
     * 优惠券规则
     */
    @NotNull
    private TemplateRule rule;
    /**
     * 当前模板是否为可用状态
     */
    private Boolean available;
}
