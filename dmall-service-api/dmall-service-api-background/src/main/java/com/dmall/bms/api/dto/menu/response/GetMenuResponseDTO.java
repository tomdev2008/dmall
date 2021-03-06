package com.dmall.bms.api.dto.menu.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 菜单响应实体
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Data
@ApiModel(value = "GetMenuResponseDTO", description = "菜单响应实体")
public class GetMenuResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    @ApiModelProperty(value = "上级id", position = 3)
    private Long parentId;

    @ApiModelProperty(value = "类型 1-目录;2-菜单", position = 4)
    private Integer type;

    @ApiModelProperty(value = "地址", position = 5)
    private String url;

    @ApiModelProperty(value = "图标", position = 6)
    private String icon;

    @ApiModelProperty(value = "默认是否打开", position = 7)
    private String open;

    @ApiModelProperty(value = "上级目录名称", position = 8)
    private String parentName;

    @ApiModelProperty(value = "菜单打开方式", position = 9)
    private String target;

    @ApiModelProperty(value = "排序", position = 10)
    private Integer sort;
}
