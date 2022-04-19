package com.daichen.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:26
 * @description 优惠券
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponInfo {

    private Long id;
    /**
     * 优惠券模板 Id
     */
    private Long templateId;
    /**
     * 领券用户 Id
     */
    private Long userId;
    /**
     * 适用门店 Id
     */
    private Long shopId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 优惠券的模板信息
     */
    private CouponTemplateInfo template;
}
