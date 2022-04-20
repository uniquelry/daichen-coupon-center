package com.daichen.coupon.customer.controller;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.api.beans.SimulationOrder;
import com.daichen.coupon.calculation.api.beans.SimulationResponse;
import com.daichen.coupon.customer.api.beans.RequestCoupon;
import com.daichen.coupon.customer.api.beans.SearchCoupon;
import com.daichen.coupon.customer.dao.entity.Coupon;
import com.daichen.coupon.customer.service.CouponCustomerService;
import com.daichen.coupon.template.api.beans.CouponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:43
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/couponCustomer")
public class CouponCustomerController {

    @Autowired
    private CouponCustomerService couponCustomerService;

    /**
     * 领取优惠券
     *
     * @param request
     * @return
     */
    @PostMapping("requestCoupon")
    public Coupon requestCoupon(@Valid @RequestBody RequestCoupon request) {
        return couponCustomerService.requestCoupon(request);
    }

    /**
     * 核销优惠券
     *
     * @param info
     * @return
     */
    @PostMapping("placeOrder")
    public ShoppingCart placeOrder(@Valid @RequestBody ShoppingCart info) {
        return couponCustomerService.placeOrder(info);
    }

    /**
     * 订单优惠券试算
     *
     * @param order
     * @return
     */
    @PostMapping("simulateOrder")
    public SimulationResponse simulateOrder(@Valid @RequestBody SimulationOrder order) {
        return couponCustomerService.simulateOrder(order);
    }

    /**
     * 删除用户优惠券
     *
     * @param userId
     * @param couponId
     */
    @DeleteMapping("deleteCoupon")
    public void deleteCoupon(@RequestParam("userId") Long userId, @RequestParam("couponId") Long couponId) {
        couponCustomerService.deleteCoupon(userId, couponId);
    }

    /**
     * 查询用户优惠券
     *
     * @param request
     * @return
     */
    @PostMapping("searchCoupon")
    public List<CouponInfo> searchCoupon(@Valid @RequestBody SearchCoupon request) {
        return couponCustomerService.findCoupon(request);
    }
}
