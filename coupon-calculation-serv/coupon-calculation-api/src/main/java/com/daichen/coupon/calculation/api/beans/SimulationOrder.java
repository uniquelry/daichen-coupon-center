package com.daichen.coupon.calculation.api.beans;

import com.daichen.coupon.template.api.beans.CouponInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:14
 * @description 计算最优的优惠券
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationOrder {

    /**
     * 商品列表
     */
    @NotEmpty
    private List<Product> products;
    /**
     * 券 Id 列表
     */
    @NotEmpty
    private List<Long> couponIds;
    /**
     * 券列表
     */
    private List<CouponInfo> couponInfos;
    /**
     * 用户 Id
     */
    @NotNull
    private Long userId;
}
