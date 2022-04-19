package com.daichen.coupon.customer.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:28
 * @description 用户领券
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCoupon {

    /**
     * 用户 Id
     */
    @NotNull
    private Long userId;

    /**
     * 券模板 Id
     */
    @NotNull
    private Long couponTemplateId;
}
