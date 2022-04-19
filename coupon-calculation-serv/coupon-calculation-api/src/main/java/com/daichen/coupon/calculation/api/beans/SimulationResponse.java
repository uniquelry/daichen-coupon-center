package com.daichen.coupon.calculation.api.beans;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:15
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationResponse {

    /**
     * 最省钱的 couponId
     */
    private Long bestCouponId;
    /**
     * 每一个 coupon 对应的 order 价格
     */
    private Map<Long, Long> couponToOrderPrice = Maps.newHashMap();
}
