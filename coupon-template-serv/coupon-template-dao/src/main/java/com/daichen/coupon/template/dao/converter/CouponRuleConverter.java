package com.daichen.coupon.template.dao.converter;

import com.daichen.coupon.template.api.beans.rules.TemplateRule;
import com.google.gson.Gson;

import javax.persistence.AttributeConverter;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:59
 * @description
 */
public class CouponRuleConverter implements AttributeConverter<TemplateRule, String> {

    @Override
    public String convertToDatabaseColumn(TemplateRule rule) {
        return new Gson().toJson(rule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String rule) {
        return new Gson().fromJson(rule, TemplateRule.class);
    }
}
