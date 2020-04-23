package com.dmall.oms.service.impl.order.handler.buyer;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.CancelTypeEnum;
import com.dmall.oms.api.enums.OmsErrorEnum;
import com.dmall.oms.api.enums.OrderOperateEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.impl.support.OrderLogSupport;
import com.dmall.oms.service.impl.support.OrderStatusSupport;
import com.dmall.oms.service.impl.support.SyncEsOrderSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 取消订单处理器
 * @author: created by hang.yu on 2020/4/4 14:57
 */
@Component
public class CancelOrderHandler extends AbstractCommonHandler<Long, OrderDO, Long> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderStatusSupport orderStatusSupport;

    @Autowired
    private OrderLogSupport orderLogSupport;

    @Autowired
    private SyncEsOrderSupport syncEsOrderSupport;

    @Override
    public BaseResult<Long> processor(Long orderId) {
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            return ResultUtil.fail(OmsErrorEnum.ORDER_NOT_EXISTS);
        }
        if (!OrderStatusEnum.WAIT_PAY.getCode().equals(orderDO.getStatus())) {
            return ResultUtil.fail(OmsErrorEnum.CANCEL_STATUS_ERROR);
        }
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (!portalMemberDTO.getId().equals(orderDO.getCreator())){
            return ResultUtil.fail(OmsErrorEnum.NO_AUTHORITY);
        }
        orderDO.setStatus(OrderStatusEnum.CANCELED.getCode());
        orderDO.setCancelTime(new Date());
        orderDO.setCancelType(CancelTypeEnum.HANDLE.getCode());
        orderMapper.updateById(orderDO);
        orderStatusSupport.insert(orderId, OrderStatusEnum.CANCELED.getCode());
        orderLogSupport.insert(orderId, OrderOperateEnum.CANCEL, false);
        syncEsOrderSupport.sendOrderEsMq(orderId);
        // 调用支付宝的关闭交易接口
        return ResultUtil.success(orderId);
    }
}
