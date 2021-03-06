package com.dmall.pms.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.dmall.pms.generator.service.ICategoryBrandService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类品牌关系表 服务实现类
 * </p>
 *
 * @author hang.yu
 * @since 2019-11-24
 */
@Service
public class ICategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrandDO>
    implements ICategoryBrandService {

}
