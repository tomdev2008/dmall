package com.dmall.bms.service.impl.rolepermission.handler;

import com.dmall.bms.api.dto.rolepermission.request.SaveRolePermissionRequestDTO;
import com.dmall.bms.service.impl.rolepermission.enums.RolePermissionErrorEnum;
import com.dmall.bms.generator.dataobject.RolePermissionDO;
import com.dmall.bms.generator.mapper.RolePermissionMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增后台角色-资源处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class SaveRolePermissionHandler extends AbstractCommonHandler<SaveRolePermissionRequestDTO, RolePermissionDO, Long> {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public BaseResult<Long> validate(SaveRolePermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveRolePermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
