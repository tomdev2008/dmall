package com.dmall.pms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 校验创建订单枚举
 * @author: created by hang.yu on 2020/3/28 16:06
 */
@Getter
@AllArgsConstructor
public enum CheckCreateOrderErrorEnum implements ErrorCodeEnum {

    SKU_PRICE_CHANGE("1001","价格发生改变,请重新下单"),

    SKU_TOTAL_PRICE_CHANGE("1002","商品总价不允许修改,请重新下单"),

    FREIGHT_CHANGE("1003","运费不允许修改,请重新下单"),

    ORDER_PRICE_CHANGE("1004","订单总价不允许修改,请重新下单"),

    INSUFFICIENT_INVENTORY("1005","很抱歉,库存不足"),


    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

}
