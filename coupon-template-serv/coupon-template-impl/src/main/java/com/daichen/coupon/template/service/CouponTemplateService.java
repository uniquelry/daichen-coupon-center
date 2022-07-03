package com.daichen.coupon.template.service;

import com.daichen.coupon.template.api.beans.CouponTemplateInfo;
import com.daichen.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.daichen.coupon.template.api.beans.TemplateSearchParams;

import java.util.List;
import java.util.Map;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 22:56
 * @description 优惠券模板类相关操作
 */
public interface CouponTemplateService {

    /**
     * 创建优惠券模板
     *
     * @param request
     * @return
     */
    CouponTemplateInfo createTemplate(CouponTemplateInfo request);

    /**
     * 克隆优惠券模板
     *
     * @param templateId
     * @return
     */
    CouponTemplateInfo cloneTemplate(Long templateId);

    /**
     * 将优惠券模板无效化
     *
     * @param id
     */
    void deleteTemplate(Long id);

    /**
     * 通过模板 Id 查询优惠券模板
     *
     * @param id
     * @return
     */
    CouponTemplateInfo getTemplateInfo(Long id);

    /**
     * 批量查询优惠券模板。
     * key = 模板 Id
     * value = 模板详情
     *
     * @param ids
     * @return
     */
    Map<Long, CouponTemplateInfo> getTemplateInfoMap(List<Long> ids);

    /**
     * 查询优惠券模板（分页）
     *
     * @param request
     * @return
     */
    PagedCouponTemplateInfo searchTemplateInfo(TemplateSearchParams request);
}
