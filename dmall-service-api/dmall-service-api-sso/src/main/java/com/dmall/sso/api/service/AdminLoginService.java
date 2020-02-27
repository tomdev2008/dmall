package com.dmall.sso.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.sso.api.dto.admin.AdminLoginRequestDTO;
import com.dmall.sso.api.dto.admin.AdminLoginResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @description: 后台登录服务
 * @author: created by hang.yu on 2020/1/6 23:07
 */
@Api(tags = "后台登录服务")
@Validated
@RequestMapping("/admin")
public interface AdminLoginService {

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    BaseResult<AdminLoginResponseDTO> login(@Valid @RequestBody AdminLoginRequestDTO requestDTO);

    @PostMapping("/logout")
    @ApiOperation(value = "登出")
    BaseResult<Void> logout(@NotBlank(message = "token不能为空") @RequestParam String token);

    @GetMapping("/checkToken")
    @ApiOperation(value = "校验token")
    BaseResult<AdminUserDTO> checkToken(@NotBlank(message = "token不能为空") @RequestParam String token);

}
