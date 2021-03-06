package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.ProductAttributeValueDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.support.CategoryAttributeSupport;
import com.dmall.pms.service.support.ProductAttributeValueSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 删除属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class DeleteAttributeHandler extends AbstractCommonHandler<Long, AttributeDO, Long> {

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Autowired
    private CategoryAttributeSupport categoryAttributeSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<Long> validate(Long id) {
        // 属性必须存在
        pmsValidate.validateAttribute(id);
        // 属性下有商品 则不能删除
        List<ProductAttributeValueDO> productAttributeValueDOS = productAttributeValueSupport.listByAttributeId(id);
        if (CollUtil.isNotEmpty(productAttributeValueDOS)) {
            return ResultUtil.fail(PmsErrorEnum.CONTAINS_PRODUCT);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        // 删除属性 以及 分类-属性
        attributeCacheService.deleteById(id);
        categoryAttributeSupport.deleteByAttributeId(id);
        return ResultUtil.success(id);
    }

}
