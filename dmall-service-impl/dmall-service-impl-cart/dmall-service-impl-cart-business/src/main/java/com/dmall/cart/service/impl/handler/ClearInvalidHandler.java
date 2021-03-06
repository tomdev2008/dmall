package com.dmall.cart.service.impl.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.cart.api.dto.delete.DeleteCartRequestDTO;
import com.dmall.cart.api.dto.list.CartListResponseDTO;
import com.dmall.cart.api.dto.list.CartSkuResponseDTO;
import com.dmall.cart.generator.dataobject.CartItemDO;
import com.dmall.common.dto.BaseResult;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 清空下架商品的购物车
 * @author: created by hang.yu on 2020/3/14 22:53
 */
@Component
public class ClearInvalidHandler extends AbstractCommonHandler<Void, CartItemDO, CartListResponseDTO> {

    @Autowired
    private ListCartHandler listCartHandler;

    @Autowired
    private DeleteCartHandler deleteCartHandler;

    @Override
    public BaseResult<CartListResponseDTO> processor(Void aVoid) {
        BaseResult<CartListResponseDTO> handler = listCartHandler.handler(null);
        if (!handler.getResult()) {
            return handler;
        }
        CartListResponseDTO data = handler.getData();
        List<CartSkuResponseDTO> carts = data.getCarts();
        if (CollUtil.isEmpty(carts)) {
            return handler;
        }
        List<Long> skuIds = carts.stream().map(CartSkuResponseDTO::getSkuId).collect(Collectors.toList());
        DeleteCartRequestDTO deleteCartRequestDTO = new DeleteCartRequestDTO();
        deleteCartRequestDTO.setSkuIds(skuIds);
        return deleteCartHandler.handler(deleteCartRequestDTO);
    }
}
