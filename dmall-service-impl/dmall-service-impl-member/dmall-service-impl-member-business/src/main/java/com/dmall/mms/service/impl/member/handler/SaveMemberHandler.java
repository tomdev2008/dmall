package com.dmall.mms.service.impl.member.handler;

import com.dmall.mms.api.dto.member.request.SaveMemberRequestDTO;
import com.dmall.mms.service.impl.member.enums.MemberErrorEnum;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.generator.mapper.MemberMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增会员处理器
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Component
public class SaveMemberHandler extends AbstractCommonHandler<SaveMemberRequestDTO, MemberDO, Long> {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public BaseResult<Long> validate(SaveMemberRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveMemberRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
