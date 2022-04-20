package com.daichen.coupon.calculation.template.impl;

import com.daichen.coupon.calculation.template.AbstractRuleTemplate;
import com.daichen.coupon.calculation.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:23
 * @description 在这个寂寞的夜晚，你需要金钱的陪伴。午夜 11 点到次日凌晨 2 点之间下单，优惠金额翻倍
 */
@Slf4j
@Component
public class LonelyNightMoneyOffTemplate extends AbstractRuleTemplate implements RuleTemplate {

    /**
     * 寂寞午夜开始时间
     */
    private static final int LONELY_NIGHT_START_HOUR_OF_DAY = 23;
    /**
     * 寂寞午夜结束时间
     */
    private static final int LONELY_NIGHT_END_HOUR_OF_DAY = 2;

    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (hourOfDay >= LONELY_NIGHT_START_HOUR_OF_DAY || hourOfDay < LONELY_NIGHT_END_HOUR_OF_DAY) {
            quota *= 2;
        }

        // benefitAmount 是扣减的价格
        // 如果当前门店的商品总价 <quota，那么最多只能扣减 shopTotalAmount 的金额
        Long benefitAmount = shopTotalAmount < quota ? shopTotalAmount : quota;
        return orderTotalAmount - benefitAmount;
    }
}
