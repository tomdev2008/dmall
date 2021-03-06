package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.request.update.BasicSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.update.UpdateSkuRequestDTO;
import com.dmall.pms.api.enums.AuditTypeEnum;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.api.enums.SkuAuditStatusEnum;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.support.*;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改sku处理器
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Component
public class UpdateSkuHandler extends AbstractCommonHandler<UpdateSkuRequestDTO, SkuDO, Long> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuMediaSupport skuMediaSupport;

    @Autowired
    private SkuAttributeValueSupport skuAttributeValueSupport;

    @Autowired
    private SkuExtSupport skuExtSupport;

    @Autowired
    private SkuSupport skuSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private SkuAuditSupport skuAuditSupport;

    @Override
    public BaseResult<Long> validate(UpdateSkuRequestDTO requestDTO) {
        BasicSkuRequestDTO basicSku = requestDTO.getBasicSkuRequestDTO();
        SkuDO sku = pmsValidate.validateSku(basicSku.getId());

        // 名称必须唯一
        SkuDO skuDO = skuSupport.getByName(basicSku.getName());
        if (skuDO != null && !skuDO.getId().equals(sku.getId())) {
            return ResultUtil.fail(PmsErrorEnum.SKU_NAME_EXISTS);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateSkuRequestDTO requestDTO) {
        BasicSkuRequestDTO basicSku = requestDTO.getBasicSkuRequestDTO();
        SkuDO skuDO = BeanUtil.copyProperties(basicSku, SkuDO.class);
        // sku基本信息
        skuMapper.updateById(skuDO);
        SkuDO sku = skuMapper.selectById(basicSku.getId());
        // sku图片信息
        skuMediaSupport.saveOrDeleteSkuMedia(sku.getProductId(), sku.getId(), requestDTO.getMediaList());
        // sku属性信息
        skuAttributeValueSupport.setSkuAttributeValue(sku.getProductId(), sku.getId(),
            requestDTO.getProductAttributeValueList());
        // sku扩展信息
        skuExtSupport.setSkuExt(sku.getProductId(), sku.getId(), requestDTO.getProductAttributeValueList(),
            requestDTO.getDetailHtml(), requestDTO.getDetailMobileHtml());
        skuAuditSupport.insert(skuDO.getProductId(), skuDO.getId(), AuditTypeEnum.SKU_UPDATE);
        return ResultUtil.success(skuDO.getId());
    }

}
