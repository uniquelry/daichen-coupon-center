package com.daichen.coupon.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:54
 * @description
 * @ EnableJpaRepositories: 用于扫描 Dao @Repository，默认扫本包当下路径
 * @ EntityScan: 用于扫描 JPA 实体类 @Entity，默认扫本包当下路径
 */
@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.daichen.coupon"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.daichen.coupon.customer.dao", "com.daichen.coupon.template.dao"})
@EntityScan(basePackages = {"com.daichen.coupon.customer.dao.entity", "com.daichen.coupon.template.dao.entity"})
public class CouponCustomerImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponCustomerImplApplication.class, args);
    }

}
