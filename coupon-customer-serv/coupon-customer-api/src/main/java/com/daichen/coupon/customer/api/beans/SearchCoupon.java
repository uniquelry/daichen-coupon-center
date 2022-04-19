package com.daichen.coupon.customer.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:30
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCoupon {

    @NotNull
    private Long userId;
    private Long shopId;
    private Integer couponStatus;
}
