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
 * @description 在这个寂寞的夜晚，你需要金钱的陪伴。午夜 10 点到次日凌晨 2 点之间下单，优惠金额翻倍
 */
@Slf4j
@Component
public class LonelyNightTemplate extends AbstractRuleTemplate implements RuleTemplate {

    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (hourOfDay >= 23 || hourOfDay < 2) {
            quota *= 2;
        }

        Long benefitAmount = shopTotalAmount < quota ? shopTotalAmount : quota;
        return orderTotalAmount - benefitAmount;
    }
}
