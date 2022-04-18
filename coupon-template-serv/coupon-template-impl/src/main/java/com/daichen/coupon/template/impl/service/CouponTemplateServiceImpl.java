package com.daichen.coupon.template.impl.service;

import com.daichen.coupon.template.api.beans.CouponTemplateInfo;
import com.daichen.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.daichen.coupon.template.api.beans.TemplateSearchParams;
import com.daichen.coupon.template.dao.CouponTemplateDao;
import com.daichen.coupon.template.impl.service.intf.CouponTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 22:56
 * @description 优惠券模板类相关操作
 */
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

    @Autowired
    private CouponTemplateDao templateDao;

    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        return null;
    }

    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        return null;
    }

    @Override
    public PagedCouponTemplateInfo search(TemplateSearchParams request) {
        return null;
    }

    @Override
    public CouponTemplateInfo loadTemplateInfo(Long id) {
        return null;
    }

    @Override
    public void deleteTemplate(Long id) {

    }

    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        return null;
    }
}
