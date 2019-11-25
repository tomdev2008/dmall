package com.dmall.pms.api.dto.brand.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: 品牌操作的公共实体
 * @author: created by hang.yu on 2019/11/23 9:47
 */
@Data
public class BrandCommonRequestDTO implements Serializable {

    private static final long serialVersionUID = -3520015238996336406L;

    @ApiModelProperty(value = "品牌名称", required = true, position = 2)
    @NotBlank(message = "品牌名称不能为空")
    private String name;

    @ApiModelProperty(value = "英文名称", position = 3)
    private String englishName;

    @ApiModelProperty(value = "首字母", required = true, position = 4)
    @NotBlank(message = "首字母不能为空")
    @Length(max = 1, min = 1, message = "首字母长度固定一位")
    private String firstLetter;

    @ApiModelProperty(value = "品牌logo", position = 5)
    private String logo;

    @ApiModelProperty(value = "品牌大图", position = 6)
    private String bigPic;
}