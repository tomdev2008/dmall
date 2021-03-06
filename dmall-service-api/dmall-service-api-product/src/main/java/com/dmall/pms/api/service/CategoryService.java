package com.dmall.pms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.dtree.DTreeResponseDTO;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.SaveCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.UpdateCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.setattribute.SetAttributeRequestDTO;
import com.dmall.pms.api.dto.category.response.CategoryResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 商品分类服务
 * @author: created by hang.yu on 2019/11/24 11:59
 */
@Api(tags = "分类管理")
@RequestMapping("/category")
public interface CategoryService {

    @ApiOperation(value = "新增分类")
    @PostMapping
    BaseResult<Long> save(@Valid @RequestBody SaveCategoryRequestDTO requestDTO);

    @ApiOperation(value = "删除分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/{id}")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @ApiOperation(value = "修改分类")
    @PutMapping
    BaseResult<Long> update(@Valid @RequestBody UpdateCategoryRequestDTO requestDTO);

    @ApiOperation(value = "根据id查询分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/{id}")
    BaseResult<CategoryResponseDTO> get(@PathVariable("id") Long id);

    @ApiOperation(value = "分类列表")
    @PostMapping("/list")
    BaseResult<List<CategoryResponseDTO>> list(@Valid @RequestBody ListCategoryRequestDTO requestDTO);

    @ApiOperation(value = "分类树")
    @ApiImplicitParam(name = "parentId", value = "上级id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/tree/{parentId}/{type}")
    BaseResult<List<DTreeResponseDTO>> tree(@PathVariable("parentId") Long parentId,
        @PathVariable("type") Integer type);

    @PostMapping("/setAttribute")
    @ApiOperation(value = "设置商品属性")
    BaseResult<Void> setCategory(@Valid @RequestBody SetAttributeRequestDTO requestDTO);

    @GetMapping("/getAttributeIds/{id}")
    @ApiOperation(value = "获取属性id列表")
    @ApiImplicitParam(name = "id", value = "三级分类id", required = true, dataType = "int", paramType = "path")
    BaseResult<List<String>> getCategoryIds(@PathVariable("id") Long id);
}
