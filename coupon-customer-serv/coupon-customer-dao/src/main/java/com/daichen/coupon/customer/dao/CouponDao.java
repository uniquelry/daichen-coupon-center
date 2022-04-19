package com.daichen.coupon.customer.dao;

import com.daichen.coupon.customer.dao.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/20 00:35
 * @description
 */
public interface CouponDao extends JpaRepository<Coupon, Long> {

    /**
     * 通过 userId 和 templateId 查询券数量
     *
     * @param userId
     * @param templateId
     * @return
     */
    long countByUserIdAndTemplateId(Long userId, Long templateId);
}
