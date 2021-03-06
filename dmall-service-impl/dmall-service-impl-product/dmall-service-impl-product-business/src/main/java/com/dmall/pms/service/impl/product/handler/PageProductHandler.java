package com.dmall.pms.service.impl.product.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.api.dto.product.response.PageProductResponseDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.product.mapper.ProductPageMapper;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 商品分页处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class PageProductHandler
    extends AbstractCommonHandler<PageProductRequestDTO, ProductDO, PageProductResponseDTO> {

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private ProductPageMapper productPageMapper;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult validate(PageProductRequestDTO requestDTO) {
        if (requestDTO.getBrandId() != null) {
            pmsValidate.validateBrand(requestDTO.getBrandId());
        }
        if (requestDTO.getCategoryId() != null) {
            pmsValidate.validateThreeLevelCategory(requestDTO.getCategoryId());
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<ResponsePage<PageProductResponseDTO>> processor(PageProductRequestDTO requestDTO) {
        Page<PageProductResponseDTO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        List<PageProductResponseDTO> responseDTOS = productPageMapper.productPage(page, requestDTO).stream()
            .map(productDO -> doConvertDto(productDO, PageProductResponseDTO.class)).collect(Collectors.toList());
        return ResultUtil.success(new ResponsePage(page.getTotal(), responseDTOS));
    }

    @Override
    protected void customerConvertDto(PageProductResponseDTO result, ProductDO doo) {
        if (doo.getBrandId() != null) {
            BrandDO brandDO = brandCacheService.selectById(doo.getBrandId());
            if (brandDO != null) {
                result.setBrandName(brandDO.getName());
            }
        }
    }
}
