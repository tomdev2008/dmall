package com.dmall.oms.service.impl.order.handler.buyer;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.elasticsearch.ESDao;
import com.dmall.component.elasticsearch.entity.*;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.buyerorderpage.BuyerOrderPageRequestDTO;
import com.dmall.oms.api.dto.buyerorderpage.response.BuyerOrderPageResponseDTO;
import com.dmall.oms.api.dto.buyerorderpage.response.BuyerReceiverDTO;
import com.dmall.oms.api.dto.buyerorderpage.response.BuyerSubOrderDTO;
import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.api.enums.SubOrderStatusEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.service.impl.order.es.EsConstants;
import com.dmall.oms.service.impl.order.es.OrderEsDTO;
import com.dmall.oms.service.impl.order.es.ReceiverDTO;
import com.google.common.collect.Lists;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 买家端订单分页处理器
 * @author: created by hang.yu on 2020/4/9 22:36
 */
@Component
public class BuyerOrderPageHandler
    extends AbstractCommonHandler<BuyerOrderPageRequestDTO, OrderDO, BuyerOrderPageResponseDTO> {

    @Autowired
    private ESDao esDao;

    @Override
    public BaseResult processor(BuyerOrderPageRequestDTO requestDTO) {
        ResponsePage<OrderEsDTO> page = esDao.search(getSkuEs(requestDTO));
        return ResultUtil.success(buildResponsePage(page));
    }

    /**
     * 构建es搜索实体
     */
    private ESSearch<OrderEsDTO> getSkuEs(BuyerOrderPageRequestDTO requestDTO) {
        ESSearch<OrderEsDTO> esSearch = new ESSearch();
        esSearch.setIndexName(EsConstants.INDEX_NAME);
        esSearch.setTypeName(EsConstants.TYPE_NAME);

        // 搜索
        if (StrUtil.isNotBlank(requestDTO.getSkuName())) {
            esSearch.setSearchFields(
                Lists.newArrayList(new SearchField(EsConstants.SEARCH_FIELDS, requestDTO.getSkuName())));
        }

        // 过滤
        List<FilterField> filterFields = Lists.newArrayList();

        // 订单状态
        if (requestDTO.getOrderStatus() != null) {
            FilterField filterField =
                new FilterField(EsConstants.FILTER_ORDER_STATUS, new Object[] {requestDTO.getOrderStatus()});
            filterFields.add(filterField);
        }
        // 订单id
        if (requestDTO.getOrderId() != null) {
            FilterField filterField =
                new FilterField(EsConstants.FILTER_ORDER_ID, new Object[] {requestDTO.getOrderId()});
            filterFields.add(filterField);
        }
        // 订单创建人
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        FilterField filterField = new FilterField(EsConstants.FILTER_CREATOR, new Object[] {portalMemberDTO.getId()});
        filterFields.add(filterField);
        esSearch.setFilterFields(filterFields);

        // 设置排序 默认按照创建时间倒排
        esSearch.setSortField(new SortField(EsConstants.SORT_FIELD, SortOrder.DESC));

        // 设置分页
        esSearch.setEsPage(new ESPage(requestDTO.getCurrent() * requestDTO.getSize(),
            requestDTO.getSize()));

        esSearch.setClazz(OrderEsDTO.class);
        return esSearch;
    }

    /**
     * 构建响应实体
     */
    private ResponsePage<BuyerOrderPageResponseDTO> buildResponsePage(ResponsePage<OrderEsDTO> page) {
        List<BuyerOrderPageResponseDTO> result = page.getData().stream().map(orderEsDTO -> {
            BuyerOrderPageResponseDTO responseDTO = new BuyerOrderPageResponseDTO();
            responseDTO.setOrderId(orderEsDTO.getOrderId());
            responseDTO.setOrderStatus(EnumUtil.getCodeDescEnum(OrderStatusEnum.class, orderEsDTO.getOrderStatus()));
            responseDTO.setOrderTime(orderEsDTO.getOrderTime());
            responseDTO.setSplit(EnumUtil.getCodeDescEnum(SplitEnum.class, orderEsDTO.getSplit()));
            BuyerReceiverDTO receiver = new BuyerReceiverDTO();
            ReceiverDTO receiverDTO = orderEsDTO.getReceiver();
            receiver.setReceiverName(receiverDTO.getReceiverName());
            receiver.setReceiverPhone(receiverDTO.getReceiverPhone());
            receiver.setReceiverAddress(receiverDTO.getReceiverAddress());
            responseDTO.setReceiver(receiver);

            List<BuyerSubOrderDTO> collect = orderEsDTO.getSubOrderList().stream().map(subOrderDTO -> {
                BuyerSubOrderDTO buyerSubOrderDTO = new BuyerSubOrderDTO();
                buyerSubOrderDTO.setSubOrderId(subOrderDTO.getSubOrderId());
                buyerSubOrderDTO.setStatus(subOrderDTO.getStatus());
                List<BuyerOrderItemDTO> buyerSkuList = subOrderDTO.getSkuList().stream().map(skuDTO -> {
                    BuyerOrderItemDTO orderItem = new BuyerOrderItemDTO();
                    orderItem.setSkuId(skuDTO.getSkuId());
                    orderItem.setSkuName(skuDTO.getSkuName());
                    orderItem.setSkuMainPic(skuDTO.getSkuMainPic());
                    orderItem.setSkuNumber(skuDTO.getSkuNumber());
                    orderItem.setSkuTotalPrice(skuDTO.getSkuTotalPrice());
                    // 子订单状态为已完成时才可以售后
                    orderItem.setCanAfterSale(SubOrderStatusEnum.COMPLETED.getCode().equals(subOrderDTO.getStatus()));
                    return orderItem;
                }).collect(Collectors.toList());
                buyerSubOrderDTO.setSkuList(buyerSkuList);
                return buyerSubOrderDTO;
            }).collect(Collectors.toList());
            responseDTO.setSubOrderList(collect);

            return responseDTO;
        }).collect(Collectors.toList());
        return new ResponsePage(page.getCount(), result);
    }

}
