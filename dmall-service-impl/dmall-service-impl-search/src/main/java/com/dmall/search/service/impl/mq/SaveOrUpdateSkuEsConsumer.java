package com.dmall.search.service.impl.mq;

import com.dmall.search.service.impl.handler.ImportSkuToEsHandler;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改es里sku的信息
 * todo 新增sku  修改sku 下架sku 购买sku 评价sku 都需要发送消息到rocketmq
 * @author: created by hang.yu on 2020/3/25 23:31
 */
@Component
@RocketMQMessageListener(topic = "${sku.es.topic}",consumerGroup = "skuEsConsumer")
public class SaveOrUpdateSkuEsConsumer  implements RocketMQListener<Long> {

    @Autowired
    private ImportSkuToEsHandler importSkuToEsHandler;

    @Override
    public void onMessage(Long skuId) {
        importSkuToEsHandler.operateEsSku(skuId);
    }
}