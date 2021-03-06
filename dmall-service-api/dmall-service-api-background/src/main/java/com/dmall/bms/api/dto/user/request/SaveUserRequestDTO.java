package com.dmall.bms.api.dto.user.request;

import com.dmall.common.dto.validate.PhoneNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: 新增后台用户请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@ApiModel(value = "SaveUserRequestDTO", description = "新增后台用户请求实体")
public class SaveUserRequestDTO implements Serializable {

    private static final long serialVersionUID = -2300902524929507216L;

    @ApiModelProperty(value = "昵称", required = true, position = 2)
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @ApiModelProperty(value = "手机号", required = true, position = 3)
    @NotBlank(message = "手机号不能为空")
    @PhoneNumber
    private String phone;

    @ApiModelProperty(value = "邮箱", required = true, position = 4)
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "真实姓名", required = true, position = 6)
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @ApiModelProperty(value = "头像", position = 7)
    private String icon;

    @ApiModelProperty(value = "备注", position = 8)
    private String remark;

}
