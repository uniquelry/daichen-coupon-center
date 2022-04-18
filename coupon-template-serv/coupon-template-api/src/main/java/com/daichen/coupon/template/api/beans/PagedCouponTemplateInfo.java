package com.daichen.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:48
 * @description 创建优惠券模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedCouponTemplateInfo {

    /**
     * 优惠券模版
     */
    public List<CouponTemplateInfo> templates;
    /**
     * 页数
     */
    public int page;
    /**
     * 总数
     */
    public long total;
}
