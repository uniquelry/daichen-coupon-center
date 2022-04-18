package com.daichen.coupon.template.dao.entity;

import com.daichen.coupon.template.api.beans.rules.TemplateRule;
import com.daichen.coupon.template.api.enums.CouponType;
import com.daichen.coupon.template.dao.converter.CouponTypeConverter;
import com.daichen.coupon.template.dao.converter.CouponRuleConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:55
 * @description 优惠券模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_template")
public class CouponTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * 状态是否可用
     */
    @Column(name = "available", nullable = false)
    private Boolean available;
    /**
     * 名称
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * 适用门店-如果为空，则为全店满减券
     */
    @Column(name = "shop_id")
    private Long shopId;
    /**
     * 描述
     */
    @Column(name = "description", nullable = false)
    private String description;
    /**
     * 优惠券类型
     */
    @Column(name = "type", nullable = false)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType category;
    /**
     * 创建时间，通过 @CreateDate 注解自动填值（需要配合 @JpaAuditing 注解在启动类上生效）
     */
    @CreatedDate
    @Column(name = "created_time", nullable = false)
    private Date createdTime;
    /**
     * 优惠券核算规则，平铺成 JSON 字段
     */
    @Column(name = "rule", nullable = false)
    @Convert(converter = CouponRuleConverter.class)
    private TemplateRule rule;
}
