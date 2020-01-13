package com.dmall.bms.api.dto.userloginlog.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 后台用户登录日志公共请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonUserLoginLogRequestDTO", description = "后台用户登录日志公共请求实体")
public class CommonUserLoginLogRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "用户id", position = 2)
    private Long userId;

    @ApiModelProperty(value = "ip地址", position = 3)
    private String ip;

    @ApiModelProperty(value = "城市", position = 4)
    private String city;

    @ApiModelProperty(value = "浏览器登录类型", position = 5)
    private String userAgent;







}
