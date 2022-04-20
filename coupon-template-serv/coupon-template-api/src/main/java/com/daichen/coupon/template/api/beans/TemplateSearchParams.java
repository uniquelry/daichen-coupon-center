package com.daichen.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class TemplateSearchParams {

    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 适用门店
     */
    private Long shopId;
    /**
     * 模板是否为可用状态
     */
    private Boolean available;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 页大小
     */
    private Integer pageSize;
}
