package com.daichen.coupon.calculation.template.impl;

import com.daichen.coupon.calculation.template.AbstractRuleTemplate;
import com.daichen.coupon.calculation.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:23
 * @description 随机减钱
 */
@Slf4j
@Component
public class RandomReductionTemplate extends AbstractRuleTemplate implements RuleTemplate {

    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        // 计算使用优惠券之后的价格
        long maxBenefit = Math.min(shopTotalAmount, quota);
        int reductionAmount = new Random().nextInt(Long.valueOf(maxBenefit).intValue());
        Long newCost = orderTotalAmount - reductionAmount;

        log.debug("original price={}, new price={}", orderTotalAmount, newCost);
        return newCost;
    }
}
