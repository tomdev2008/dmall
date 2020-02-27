package com.dmall.mms.api.dto.member.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: UpdatePasswordRequestDTO
 * @author: created by hang.yu on 2020/2/27 23:05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UpdatePasswordRequestDTO", description = "修改密码请求实体")
public class UpdatePasswordRequestDTO implements Serializable {

    private static final long serialVersionUID = -1057107278158362447L;

    @ApiModelProperty(value = "会员邮箱", position = 1)
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "原密码", position = 2)
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", position = 3)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}
