package com.dmall.mms.service.impl.comment.handler;

import com.dmall.mms.service.impl.comment.enums.CommentErrorEnum;
import com.dmall.mms.generator.dataobject.CommentDO;
import com.dmall.mms.generator.mapper.CommentMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除商品评价处理器
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Component
public class DeleteCommentHandler extends AbstractCommonHandler<Long, CommentDO, Long> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}