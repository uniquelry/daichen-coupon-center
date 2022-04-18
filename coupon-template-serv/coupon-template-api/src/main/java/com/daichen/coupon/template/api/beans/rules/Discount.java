package com.daichen.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daichen
 * @version v1.0
 * @create 2022/4/18 18:42
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    /**
     * 满减 - 减掉的钱数
     * 折扣 - 90 = 9 折, 95=95 折
     */
    private Long quota;
    /**
     * 最低达到多少消费才能用
     */
    private Long threshold;
}
