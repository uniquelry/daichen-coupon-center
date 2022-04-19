package com.daichen.coupon.calculation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:54
 * @description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.daichen.coupon"})
public class CouponCalculationImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponCalculationImplApplication.class, args);
    }

}
