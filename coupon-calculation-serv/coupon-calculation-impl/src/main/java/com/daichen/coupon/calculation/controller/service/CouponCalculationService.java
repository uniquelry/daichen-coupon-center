package com.daichen.coupon.calculation.controller.service;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.api.beans.SimulationOrder;
import com.daichen.coupon.calculation.api.beans.SimulationResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:17
 * @description
 */
public interface CouponCalculationService {

    /**
     * 订单优惠券结算。这里通过 Factory 类决定使用哪个底层 Rule，底层规则对上层透明
     *
     * @param cart
     * @return
     */
    ShoppingCart calculateOrder(@RequestBody ShoppingCart cart);

    /**
     * 订单优惠券试算。给客户提示每个可用券的优惠额度，帮助挑选
     *
     * @param order
     * @return
     */
    SimulationResponse simulateOrder(@RequestBody SimulationOrder order);
}
