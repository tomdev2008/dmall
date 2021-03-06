package com.dmall.bms.service.impl.menu.handler;

import com.dmall.bms.api.dto.menu.response.GetMenuResponseDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询菜单表 处理器
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Component
public class GetMenuHandler extends AbstractCommonHandler<Long, MenuDO, GetMenuResponseDTO> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseResult<GetMenuResponseDTO> processor(Long id) {
        MenuDO menuDO = menuMapper.selectById(id);
        if (menuDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.MENU_NOT_EXIST);
        }
        GetMenuResponseDTO getMenuResponseDTO = doConvertDto(menuDO, GetMenuResponseDTO.class);
        if (menuDO.getParentId() != 0) {
            MenuDO parent = menuMapper.selectById(menuDO.getParentId());
            getMenuResponseDTO.setParentName(parent.getName());
        }
        return ResultUtil.success(getMenuResponseDTO);
    }

}
