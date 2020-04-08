package com.dmall.pay.service.impl.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pay.api.dto.PayRequestDTO;
import com.dmall.pay.api.dto.PayResponseDTO;
import com.dmall.pay.api.enums.PaymentTypeEnum;
import com.dmall.pay.generator.dataobject.PaymentInfoDO;
import com.dmall.pay.service.impl.handler.paytype.PayTypeFactory;
import com.dmall.pay.service.impl.handler.paytype.PayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 支付处理器
 * @author: created by hang.yu on 2020/4/2 23:27
 */
@Component
public class PayHandler extends AbstractCommonHandler<PayRequestDTO, PaymentInfoDO, PayResponseDTO> {

    @Autowired
    private PayTypeFactory payTypeFactory;

    @Override
    public BaseResult<PayResponseDTO> processor(PayRequestDTO requestDTO) {
        PaymentTypeEnum paymentTypeEnum = EnumUtil.getCodeDescEnum(PaymentTypeEnum.class, requestDTO.getPayType());
        PayTypeService instance = payTypeFactory.createInstance(paymentTypeEnum);
        return ResultUtil.success(instance.createPayment(requestDTO));
    }
}
