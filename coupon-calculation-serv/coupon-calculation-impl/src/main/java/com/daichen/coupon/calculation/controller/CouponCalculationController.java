package com.daichen.coupon.calculation.controller;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.api.beans.SimulationOrder;
import com.daichen.coupon.calculation.api.beans.SimulationResponse;
import com.daichen.coupon.calculation.controller.service.CouponCalculationService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:21
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/couponCalculator")
public class CouponCalculationController {

    @Autowired
    private CouponCalculationService couponCalculationService;

    /**
     * 订单优惠券结算
     *
     * @param cart
     * @return
     */
    @PostMapping("/calculateOrder")
    @ResponseBody
    public ShoppingCart calculateOrder(@RequestBody ShoppingCart cart) {
        log.info("do calculateOrder cart: {}", new Gson().toJson(cart));
        return couponCalculationService.calculateOrder(cart);
    }

    /**
     * 订单优惠券试算。给客户提示每个可用券的优惠额度，帮助挑选
     *
     * @param order
     * @return
     */
    @PostMapping("/simulateOrder")
    @ResponseBody
    public SimulationResponse simulateOrder(@RequestBody SimulationOrder order) {
        log.info("do simulateOrder order: {}", new Gson().toJson(order));
        return couponCalculationService.simulateOrder(order);
    }
}
