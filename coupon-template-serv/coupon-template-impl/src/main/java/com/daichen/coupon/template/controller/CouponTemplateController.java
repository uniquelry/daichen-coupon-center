package com.daichen.coupon.template.controller;

import com.daichen.coupon.template.api.beans.CouponTemplateInfo;
import com.daichen.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.daichen.coupon.template.api.beans.TemplateSearchParams;
import com.daichen.coupon.template.service.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 23:28
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/couponTemplate")
public class CouponTemplateController {

    @Autowired
    private CouponTemplateService couponTemplateService;

    /**
     * 创建优惠券模板
     *
     * @param request
     * @return
     */
    @PostMapping("/createTemplate")
    public CouponTemplateInfo createTemplate(@Valid @RequestBody CouponTemplateInfo request) {
        log.info("createTemplate: request={}", request);
        return couponTemplateService.createTemplate(request);
    }

    /**
     * 克隆优惠券模板
     *
     * @param templateId
     * @return
     */
    @PostMapping("/cloneTemplate")
    public CouponTemplateInfo cloneTemplate(@RequestParam("templateId") Long templateId) {
        log.info("cloneTemplate: templateId={}", templateId);
        return couponTemplateService.cloneTemplate(templateId);
    }

    /**
     * 优惠券模板无效化
     *
     * @param id
     */
    @DeleteMapping("/deleteTemplate")
    public void deleteTemplate(@RequestParam("id") Long id) {
        log.info("deleteTemplate, id={}", id);
        couponTemplateService.deleteTemplate(id);
    }

    /**
     * 获取优惠券模板
     *
     * @param id
     * @return
     */
    @GetMapping("/getTemplate")
    public CouponTemplateInfo getTemplate(@RequestParam("id") Long id) {
        log.info("getTemplate, id={}", id);
        return couponTemplateService.getTemplateInfo(id);
    }

    /**
     * 批量获取优惠券模板
     *
     * @param ids
     * @return
     */
    @GetMapping("/getTemplateBatch")
    public Map<Long, CouponTemplateInfo> getTemplateBatch(@RequestParam("ids") Collection<Long> ids) {
        log.info("getTemplateBatch: ids={}", ids);
        return couponTemplateService.getTemplateInfoMap(ids);
    }

    /**
     * 搜索优惠券模板
     *
     * @param request
     * @return
     */
    @PostMapping("/searchTemplate")
    public PagedCouponTemplateInfo searchTemplate(@Valid @RequestBody TemplateSearchParams request) {
        log.info("searchTemplate, request={}", request);
        return couponTemplateService.searchTemplateInfo(request);
    }
}
