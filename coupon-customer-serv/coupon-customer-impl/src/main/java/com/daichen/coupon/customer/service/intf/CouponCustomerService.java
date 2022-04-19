package com.daichen.coupon.customer.service.intf;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.api.beans.SimulationOrder;
import com.daichen.coupon.calculation.api.beans.SimulationResponse;
import com.daichen.coupon.customer.api.beans.RequestCoupon;
import com.daichen.coupon.customer.api.beans.SearchCoupon;
import com.daichen.coupon.customer.dao.entity.Coupon;
import com.daichen.coupon.template.api.beans.CouponInfo;

import java.util.List;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:46
 * @description 用户对接服务
 */
public interface CouponCustomerService {

    /**
     * 领取优惠券
     *
     * @param request
     * @return
     */
    Coupon requestCoupon(RequestCoupon request);

    /**
     * 核销优惠券
     *
     * @param cart
     * @return
     */
    ShoppingCart placeOrder(ShoppingCart cart);

    /**
     * 优惠券金额试算
     *
     * @param order
     * @return
     */
    SimulationResponse simulateOrderPrice(SimulationOrder order);

    /**
     * 删除用户优惠券
     *
     * @param userId
     * @param couponId
     */
    void deleteCoupon(Long userId, Long couponId);

    /**
     * 查询用户优惠券
     *
     * @param request
     * @return
     */
    List<CouponInfo> findCoupon(SearchCoupon request);
}
