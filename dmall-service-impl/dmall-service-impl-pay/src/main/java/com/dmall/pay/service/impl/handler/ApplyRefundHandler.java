package com.dmall.pay.service.impl.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pay.api.dto.applyrefund.ApplyRefundRequestDTO;
import com.dmall.pay.api.enums.PaymentErrorEnum;
import com.dmall.pay.api.enums.PaymentTypeEnum;
import com.dmall.pay.generator.dataobject.PaymentInfoDO;
import com.dmall.pay.service.impl.handler.paytype.PaymentTypeFactory;
import com.dmall.pay.service.impl.handler.paytype.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 申请退款处理器
 * @author: created by hang.yu on 2020/4/13 22:58
 */
@Component
public class ApplyRefundHandler extends AbstractCommonHandler<ApplyRefundRequestDTO, PaymentInfoDO, Void> {

    @Autowired
    private PaymentTypeFactory paymentTypeFactory;

    @Override
    public BaseResult processor(ApplyRefundRequestDTO requestDTO) {
        PaymentTypeEnum paymentTypeEnum = EnumUtil.getCodeDescEnum(PaymentTypeEnum.class, requestDTO.getPaymentType());
        if (paymentTypeEnum == null) {
            return ResultUtil.fail(PaymentErrorEnum.PAYMENT_ORDER_EXISTS);
        }
        PaymentTypeService instance = paymentTypeFactory.createInstance(paymentTypeEnum);
        instance.applyRefund(requestDTO);
        return ResultUtil.success();
    }
}
