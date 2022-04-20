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
 * @create 2022/4/18 18:07
 * @description 购物车
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart {

    /**
     * 用户 Id
     */
    @NotNull
    private Long userId;
    /**
     * 商品列表
     */
    @NotEmpty
    private List<Product> products;
    /**
     * 总金额
     */
    private Long cost;
    /**
     * 购物车下单选择的券 Ids（目前只支持单张优惠券）
     */
    private List<Long> couponIds;
    /**
     * 购物车下单选择的券信息（目前只支持单张优惠券）。但是为了以后的扩展考虑，可以添加多个优惠券的计算逻辑
     */
    private List<CouponInfo> couponInfos;
}
