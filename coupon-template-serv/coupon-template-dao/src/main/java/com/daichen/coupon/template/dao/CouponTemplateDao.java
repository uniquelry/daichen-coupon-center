package com.daichen.coupon.template.dao;

import com.daichen.coupon.template.dao.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 20:54
 * @description
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate, Long> {

    /**
     * 根据 shopId + 可用状态查询店铺有多少券模板
     *
     * @param shopId
     * @param available
     * @return
     */
    Integer countByShopIdAndAvailable(Long shopId, Boolean available);

    /**
     * 将券模板设置为不可用
     *
     * @param id
     * @return
     */
    @Modifying
    @Query("update CouponTemplate c set c.available = 0 where c.id = :id")
    int updateCouponUnavailable(@Param("id") Long id);
}
