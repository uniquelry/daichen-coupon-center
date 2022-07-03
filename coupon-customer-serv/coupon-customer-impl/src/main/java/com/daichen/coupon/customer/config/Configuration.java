package com.daichen.coupon.customer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/6/29 22:08
 * @description
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    /**
     * 注册 Bean 并添加负载均衡功能
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder register() {
        return WebClient.builder();
    }

    /**
     * 注册 Bean 并添加负载均衡功能
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
