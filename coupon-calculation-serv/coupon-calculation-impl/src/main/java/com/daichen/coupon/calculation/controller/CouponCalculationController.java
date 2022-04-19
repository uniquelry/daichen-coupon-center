package com.daichen.coupon.calculation.controller;

import com.alibaba.fastjson.JSON;
import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.api.beans.SimulationOrder;
import com.daichen.coupon.calculation.api.beans.SimulationResponse;
import com.daichen.coupon.calculation.controller.service.intf.CouponCalculationService;
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
@RequestMapping("calculator")
public class CouponCalculationController {

    @Autowired
    private CouponCalculationService calculationService;

    /**
     * 优惠券结算
     *
     * @param cart
     * @return
     */
    @PostMapping("/calculateOrderPrice")
    @ResponseBody
    public ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart cart) {
        log.info("do calculateOrderPrice cart: {}", JSON.toJSONString(cart));
        return calculationService.calculateOrderPrice(cart);
    }

    /**
     * 优惠券列表挨个试算。给客户提示每个可用券的优惠额度，帮助挑选
     *
     * @param order
     * @return
     */
    @PostMapping("/simulateOrder")
    @ResponseBody
    public SimulationResponse simulateOrder(@RequestBody SimulationOrder order) {
        log.info("do simulateOrder order: {}", JSON.toJSONString(order));
        return calculationService.simulateOrder(order);
    }
}
