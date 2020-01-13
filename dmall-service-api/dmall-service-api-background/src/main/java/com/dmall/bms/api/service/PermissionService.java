package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.permission.request.ListPermissionRequestDTO;
import com.dmall.bms.api.dto.permission.request.PagePermissionRequestDTO;
import com.dmall.bms.api.dto.permission.common.CommonPermissionResponseDTO;
import com.dmall.bms.api.dto.permission.request.SavePermissionRequestDTO;
import com.dmall.bms.api.dto.permission.request.UpdatePermissionRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 资源服务
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Api(tags = "资源服务")
@RequestMapping("/permission")
public interface PermissionService {

    @PostMapping("/")
    @ApiOperation(value = "新增资源")
    BaseResult<Long> save(@Valid @RequestBody SavePermissionRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除资源")
    @ApiImplicitParam(name = "id", value = "资源id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改资源")
    BaseResult<Long> update(@Valid @RequestBody UpdatePermissionRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询资源")
    @ApiImplicitParam(name = "id", value = "资源id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonPermissionResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "资源列表")
    BaseResult<List<CommonPermissionResponseDTO>>list(@RequestBody ListPermissionRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "资源分页")
    BaseResult<LayUiPage<CommonPermissionResponseDTO>>page(@RequestBody PagePermissionRequestDTO requestDTO);

}
