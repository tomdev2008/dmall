package com.dmall.pms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.CategoryProductDO;
import com.dmall.pms.generator.mapper.CategoryProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: CategoryProductSupport
 * @author: created by hang.yu on 2019/12/29 17:53
 */
@Component
public class CategoryProductSupport {

    @Autowired
    private CategoryProductMapper categoryProductMapper;

    /**
     * 根据分类id查询
     */
    public List<CategoryProductDO> listByCategoryId(Long categoryId) {
        return categoryProductMapper.selectList(Wrappers.<CategoryProductDO>lambdaQuery()
            .eq(CategoryProductDO::getCategoryId, categoryId));
    }

    /**
     * 根据商品id查询
     */
    public List<CategoryProductDO> listByProductId(Long productId) {
        return categoryProductMapper.selectList(Wrappers.<CategoryProductDO>lambdaQuery()
            .eq(CategoryProductDO::getProductId, productId));
    }
}
