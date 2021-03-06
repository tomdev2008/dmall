package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.dto.user.response.UserResponseDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询后台用户处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class GetCurrentUserHandler extends AbstractCommonHandler<Void, UserDO, UserResponseDTO> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResult<UserResponseDTO> processor(Void v) {
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        UserDO userDO = userMapper.selectById(adminUserDTO.getId());
        if (userDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.USER_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(userDO, UserResponseDTO.class));
    }

}
