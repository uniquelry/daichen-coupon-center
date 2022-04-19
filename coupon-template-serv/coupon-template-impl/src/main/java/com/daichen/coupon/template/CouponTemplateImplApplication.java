package com.daichen.coupon.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:54
 * @description
 */
@SpringBootApplication
//@EnableJpaAuditing
public class CouponTemplateImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponTemplateImplApplication.class, args);
    }

}
