package com.dmall.sso.service.impl.admin;

import com.dmall.common.dto.BaseResult;
import com.dmall.sso.api.dto.PermissionResponseDTO;
import com.dmall.sso.api.dto.RoleResponseDTO;
import com.dmall.sso.api.service.AdminPermissionService;
import com.dmall.sso.service.impl.admin.handler.UserPermissionHandler;
import com.dmall.sso.service.impl.admin.handler.UserRoleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @description: AdminPermissionServiceImpl
 * @author: created by hang.yu on 2020/1/12 10:32
 */
@RestController
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Autowired
    private UserRoleHandler userRoleHandler;

    @Autowired
    private UserPermissionHandler userPermissionHandler;

    @Override
    public BaseResult<List<RoleResponseDTO>> listRoles(@NotBlank(message = "用户名称不能为空") String userName) {
        return userRoleHandler.handler(userName);
    }

    @Override
    public BaseResult<List<PermissionResponseDTO>> listPermissions(@NotBlank(message = "用户名称不能为空") String userName) {
        return userPermissionHandler.handler(userName);
    }
}
