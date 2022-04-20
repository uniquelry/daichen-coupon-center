package com.daichen.coupon.calculation.template;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:22
 * @description
 */
public interface RuleTemplate {

    /**
     * 计算购物车总价
     *
     * @param cart
     * @return
     */
    ShoppingCart calculate(ShoppingCart cart);
}
