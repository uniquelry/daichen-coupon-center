package com.daichen.coupon.calculation.template.impl;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.template.AbstractRuleTemplate;
import com.daichen.coupon.calculation.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:23
 * @description 空实现
 */
@Slf4j
@Component
public class DummyTemplate extends AbstractRuleTemplate implements RuleTemplate {

    @Override
    public ShoppingCart calculate(ShoppingCart cart) {
        // 获取订单总价
        cart.setCost(getTotalPrice(cart.getProducts()));
        return cart;
    }

    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        return orderTotalAmount;
    }
}
