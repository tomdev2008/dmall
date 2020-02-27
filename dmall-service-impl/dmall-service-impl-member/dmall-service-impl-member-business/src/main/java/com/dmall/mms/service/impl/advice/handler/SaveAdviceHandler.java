package com.dmall.mms.service.impl.advice.handler;

import com.dmall.mms.api.dto.advice.request.SaveAdviceRequestDTO;
import com.dmall.mms.service.impl.advice.enums.AdviceErrorEnum;
import com.dmall.mms.generator.dataobject.AdviceDO;
import com.dmall.mms.generator.mapper.AdviceMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增会员意见表 处理器
 * @author: created by hang.yu on 2020-02-23 19:41:02
 */
@Component
public class SaveAdviceHandler extends AbstractCommonHandler<SaveAdviceRequestDTO, AdviceDO, Long> {

    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    public BaseResult<Long> validate(SaveAdviceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveAdviceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
