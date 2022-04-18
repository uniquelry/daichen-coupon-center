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
 * @create 2022/4/18 18:14
 * @description 试算最优的优惠券
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationOrder {

    @NotEmpty
    private List<Product> products;

    @NotEmpty
    private List<Long> couponIds;

    private List<CouponInfo> couponInfos;

    @NotNull
    private Long userId;
}
