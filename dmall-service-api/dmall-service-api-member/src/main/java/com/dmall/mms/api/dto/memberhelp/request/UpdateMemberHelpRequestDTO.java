package com.dmall.mms.api.dto.memberhelp.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.memberhelp.common.CommonMemberHelpRequestDTO;

/**
 * @description: 修改会员-帮助关系表 帮助对会员有用请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateMemberHelpRequestDTO", description = "修改会员-帮助关系表 帮助对会员有用请求实体")
public class UpdateMemberHelpRequestDTO extends CommonMemberHelpRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
