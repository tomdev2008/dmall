package com.dmall.oms.service.impl.cart.handler;

import com.alibaba.fastjson.JSON;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.CookieUtil;
import com.dmall.common.util.RequestUtil;
import com.dmall.common.util.ResponseUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.cart.list.CartListResponseDTO;
import com.dmall.oms.api.dto.cart.select.SelectCartRequestDTO;
import com.dmall.oms.api.dto.cart.select.SelectTypeEnum;
import com.dmall.oms.generator.dataobject.CartItemDO;
import com.dmall.oms.generator.mapper.CartItemMapper;
import com.dmall.oms.service.impl.cart.cache.CartCacheService;
import com.dmall.oms.service.impl.cart.cache.Constants;
import com.dmall.oms.service.impl.cart.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 勾选或取消勾选商品
 * @author: created by hang.yu on 2020/3/14 18:11
 */
@Component
public class SelectCartHandler extends AbstractCommonHandler<SelectCartRequestDTO, CartItemDO, CartListResponseDTO> {

    @Autowired
    private CartCacheService cartCacheService;

    @Autowired
    private ListCartHandler listCartHandler;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public BaseResult<CartListResponseDTO> processor(SelectCartRequestDTO requestDTO) {
        // 获取当前登录用户
        PortalMemberDTO login = PortalMemberContextHolder.get();
        if (login == null) {
            notLoginUpdateCart(requestDTO.getType(), requestDTO.getSkuIds());
        } else {
            loginUpdateCart(requestDTO.getType(), requestDTO.getSkuIds(), login.getId());
        }
        return listCartHandler.handler(null);
    }


    /**
     * 未登录时修改购物车
     */
    private void notLoginUpdateCart(Integer type, List<Long> skuIds) {
        // 查询cookie中的购物车数据
        String cartJson = CookieUtil.getCookie(RequestUtil.getRequest(), Constants.COOKIE_NAME, true);
        List<CartDTO> cartDTOS = JSON.parseArray(cartJson, CartDTO.class);

        for (CartDTO cartDTO : cartDTOS) {
            if (skuIds.contains(cartDTO.getSkuId())) {
                cartDTO.setChecked(type.equals(SelectTypeEnum.CHECK.getCode()));
            }
        }
        CookieUtil.addCookie(ResponseUtil.getResponse(), Constants.COOKIE_NAME, JSON.toJSONString(cartDTOS), Constants.COOKIE_STORE_TIME, true);
    }

    /**
     * 登录后修改购物车
     */
    private void loginUpdateCart(Integer type, List<Long> skuIds, Long memberId) {
        List<CartItemDO> cartItemDOS = cartCacheService.list(memberId);
        for (CartItemDO cartItemDO : cartItemDOS) {
            if (skuIds.contains(cartItemDO.getSkuId())) {
                cartItemDO.setChecked(type.equals(SelectTypeEnum.CHECK.getCode()));
                cartCacheService.insert(memberId, cartItemDO);
                cartItemMapper.updateById(cartItemDO);
            }
        }
    }

}
