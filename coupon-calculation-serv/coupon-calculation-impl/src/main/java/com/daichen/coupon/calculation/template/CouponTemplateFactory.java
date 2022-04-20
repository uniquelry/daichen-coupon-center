package com.daichen.coupon.calculation.template;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.template.impl.AntiPauTemplate;
import com.daichen.coupon.calculation.template.impl.DiscountTemplate;
import com.daichen.coupon.calculation.template.impl.DummyTemplate;
import com.daichen.coupon.calculation.template.impl.LonelyNightMoneyOffTemplate;
import com.daichen.coupon.calculation.template.impl.MoneyOffTemplate;
import com.daichen.coupon.calculation.template.impl.RandomReductionTemplate;
import com.daichen.coupon.template.api.beans.CouponTemplateInfo;
import com.daichen.coupon.template.api.enums.CouponType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:22
 * @description 工厂方法根据订单中的优惠券信息，返回对应的 Template 用于计算优惠价
 */
@Slf4j
@Component
public class CouponTemplateFactory {

    @Autowired
    private MoneyOffTemplate moneyOffTemplate;
    @Autowired
    private DiscountTemplate discountTemplate;
    @Autowired
    private RandomReductionTemplate randomReductionTemplate;
    @Autowired
    private LonelyNightMoneyOffTemplate lonelyNightMoneyOffTemplate;
    @Autowired
    private DummyTemplate dummyTemplate;
    @Autowired
    private AntiPauTemplate antiPauTemplate;

    public RuleTemplate getTemplate(ShoppingCart cart) {
        // 不使用优惠券
        if (CollectionUtils.isEmpty(cart.getCouponInfos())) {
            // dummy 模板直接返回原价，不进行优惠计算
            return dummyTemplate;
        }

        // 获取优惠券的类别
        // 目前每个订单只支持单张优惠券
        CouponTemplateInfo template = cart.getCouponInfos().get(0).getTemplate();
        CouponType category = CouponType.convert(template.getType());

        switch (category) {
            // 满减券
            case MONEY_OFF:
                return moneyOffTemplate;
            // 打折券
            case DISCOUNT:
                return discountTemplate;
            // 随机立减券
            case RANDOM_REDUCTION:
                return randomReductionTemplate;
            // 寂寞午夜翻倍券
            case LONELY_NIGHT_MONEY_OFF:
                return lonelyNightMoneyOffTemplate;
            // PUA 加倍奉还券
            case ANTI_PUA:
                return antiPauTemplate;
            // 未知类型的券模板
            default:
                return dummyTemplate;
        }
    }
}
