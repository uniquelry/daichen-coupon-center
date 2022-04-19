package com.daichen.coupon.calculation.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:04
 * @description 商品
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    /**
     * 可以试着搭建一个商品中心，用来存储商品信息，逐步完善这个应用
     */
    private Long productId;
    /**
     * 商品的价格
     */
    private Long price;
    /**
     * 商品在购物车里的数量
     */
    private Integer count;
    /**
     * 商品销售的门店
     */
    private Long shopId;
}
