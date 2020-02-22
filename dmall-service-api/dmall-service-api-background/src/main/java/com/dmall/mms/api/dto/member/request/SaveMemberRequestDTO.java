package com.dmall.mms.api.dto.member.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.member.common.CommonMemberRequestDTO;

/**
 * @description: 新增会员请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveMemberRequestDTO", description = "新增会员请求实体")
public class SaveMemberRequestDTO extends CommonMemberRequestDTO {

}
