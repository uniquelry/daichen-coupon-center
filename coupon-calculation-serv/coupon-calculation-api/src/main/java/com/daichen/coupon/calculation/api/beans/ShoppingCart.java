package com.daichen.coupon.calculation.api.beans;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:07
 * @description 封装了订单信心
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    @NotEmpty
    private List<Product> products;

    private Long couponId;

    private long cost;

    // 目前只支持单张优惠券
    // 但是为了以后的扩展考虑，你可以添加多个优惠券的计算逻辑
    private List<CouponInfo> couponInfos;

    @NotNull
    private Long userId;
}
