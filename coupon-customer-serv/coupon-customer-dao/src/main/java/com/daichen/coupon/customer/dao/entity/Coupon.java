package com.daichen.coupon.customer.dao.entity;

import com.daichen.coupon.customer.api.enums.CouponStatus;
import com.daichen.coupon.customer.dao.convertor.CouponStatusConverter;
import com.daichen.coupon.template.api.beans.CouponTemplateInfo;
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
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:37
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * 创建时间，通过 @CreateDate 注解自动填值（需要配合 @JpaAuditing 注解在启动类上生效）
     */
    @CreatedDate
    @Column(name = "created_time", nullable = false)
    private Date createdTime;
    /**
     * 对应的模板 Id - 不使用 one to one 映射
     * 不推荐使用级联查询的原因是为了防止滥用而导致的 DB 性能问题
     */
    @Column(name = "template_id", nullable = false)
    private Long templateId;
    /**
     * 所有者的用户 Id
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;
    /**
     * 冗余一个 shopId 方便查找
     */
    @Column(name = "shop_id")
    private Long shopId;
    /**
     * 优惠券的使用/未使用状态
     */
    @Column(name = "status", nullable = false)
    @Convert(converter = CouponStatusConverter.class)
    private CouponStatus status;
    /**
     * 被 Transient 标记的属性是不会被持久化的
     */
    @Transient
    private CouponTemplateInfo templateInfo;
}
