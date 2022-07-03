package com.daichen.coupon.customer.remote;

import com.daichen.coupon.calculation.api.beans.ShoppingCart;
import com.daichen.coupon.calculation.api.beans.SimulationOrder;
import com.daichen.coupon.calculation.api.beans.SimulationResponse;
import com.daichen.coupon.template.api.beans.CouponTemplateInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/7/3 19:10
 * @description
 */
@Component
public class RemoteWebClientService {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private RestTemplate restTemplate;

    public CouponTemplateInfo getTemplateInfoCall(Long templateId) {
//        return webClientBuilder.build()
//                .get()
//                .uri("http://coupon-template-serv/couponTemplate/getTemplate?id=" + templateId)
//                .retrieve()
//                .bodyToMono(CouponTemplateInfo.class).block();
        return restTemplate.getForObject("http://coupon-template-serv/couponTemplate/getTemplate?id=" + templateId,
                CouponTemplateInfo.class);
    }

    public Map<Long, CouponTemplateInfo> getTemplateInfoMapCall(List<Long> templateIds) {
        return webClientBuilder.build()
                .get()
                .uri("http://coupon-template-serv/couponTemplate/getTemplateBatch?ids=" + StringUtils.join(templateIds, ","))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<Long, CouponTemplateInfo>>() {
                }).block();
    }

    public ShoppingCart calculateOrderCall(ShoppingCart cart) {
        return webClientBuilder.build()
                .post()
                .uri("http://coupon-calculation-serv/couponCalculator/calculateOrder")
                .bodyValue(cart)
                .retrieve().bodyToMono(ShoppingCart.class).block();
    }

    public SimulationResponse simulateOrderCall(SimulationOrder order) {
        return webClientBuilder.build()
                .post()
                .uri("http://coupon-calculation-serv/couponCalculator/simulateOrder")
                .bodyValue(order)
                .retrieve().bodyToMono(SimulationResponse.class).block();
    }
}
