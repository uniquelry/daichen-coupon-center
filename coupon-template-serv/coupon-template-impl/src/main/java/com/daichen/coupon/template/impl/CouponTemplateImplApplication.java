package com.daichen.coupon.template.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liuruiyu
 */
@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.daichen"})
public class CouponTemplateImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponTemplateImplApplication.class, args);
    }

}
