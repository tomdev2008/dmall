package com.dmall.pms.service.impl.sku.handler;

import cn.hutool.core.util.NumberUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.util.FreightMoneyUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.request.CheckCreateOrderRequestDTO;
import com.dmall.pms.api.dto.sku.request.CheckOrderSkuRequestDTO;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.dmall.pms.api.enums.CheckCreateOrderErrorEnum;
import com.dmall.pms.generator.dataobject.SkuDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 提交订单校验处理器
 * @author: created by hang.yu on 2020/3/29 11:38
 */
@Component
public class CreateOrderCheckHandler extends AbstractCommonHandler<CheckCreateOrderRequestDTO, SkuDO, List<BasicSkuResponseDTO>> {

    @Autowired
    private GetBasicSkuHandler getBasicSkuHandler;

    @Override
    public BaseResult<List<BasicSkuResponseDTO>> processor(CheckCreateOrderRequestDTO requestDTO) {
        List<CheckOrderSkuRequestDTO> orderSku = requestDTO.getOrderSku();
        List<Long> skuIds = orderSku.stream().map(CheckOrderSkuRequestDTO::getSkuId).collect(Collectors.toList());
        Map<Long, CheckOrderSkuRequestDTO> skuMap = orderSku.stream()
                .collect(Collectors.toMap(CheckOrderSkuRequestDTO::getSkuId, checkOrderSkuRequestDTO -> checkOrderSkuRequestDTO));

        // step1. 调用sku接口获取sku信息
        BaseResult<List<BasicSkuResponseDTO>> skuResponse = getBasicSkuHandler.processor(skuIds);
        if (!skuResponse.getResult()) {
            return ResultUtil.fail(BasicStatusEnum.FAIL);
        }

        List<BasicSkuResponseDTO> skuData = skuResponse.getData();

        //  step2. 验证价格
        BigDecimal skuTotalPrice = BigDecimal.ZERO;
        for (BasicSkuResponseDTO sku : skuData) {
            if (!NumberUtil.equals(skuMap.get(sku.getId()).getSkuPrice(), sku.getPrice())) {
                return ResultUtil.fail(CheckCreateOrderErrorEnum.SKU_PRICE_CHANGE);
            }
            skuTotalPrice = skuTotalPrice.add(sku.getPrice());
        }
        if (!NumberUtil.equals(skuTotalPrice, requestDTO.getTotalSkuMoney())) {
            return ResultUtil.fail(CheckCreateOrderErrorEnum.SKU_TOTAL_MONEY_CHANGE);
        }
        BigDecimal freightMoney = FreightMoneyUtil.getFreightMoney(skuTotalPrice);
        if (!NumberUtil.equals(freightMoney, requestDTO.getFreightMoney())) {
            return ResultUtil.fail(CheckCreateOrderErrorEnum.FREIGHT_CHANGE);
        }
        if (!NumberUtil.equals(NumberUtil.add(skuTotalPrice, freightMoney), requestDTO.getOrderMoney())) {
            return ResultUtil.fail(CheckCreateOrderErrorEnum.ORDER_MONEY_CHANGE);
        }

        // step3. 验证库存
        for (BasicSkuResponseDTO sku : skuData) {
            if (skuMap.get(sku.getId()).getCount() > sku.getSalableStock()) {
                return ResultUtil.fail(CheckCreateOrderErrorEnum.INSUFFICIENT_INVENTORY);
            }
        }
        return skuResponse;
    }
}