package com.dmall.pms.api.dto.product.request;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.UnitEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 商品基础信息实体
 * @author: created by hang.yu on 2019/12/10 21:43
 */
@Data
@ApiModel(value = "BasicProductRequestDTO", description = "商品基础信息实体")
public class BasicProductRequestDTO implements Serializable {

    private static final long serialVersionUID = 1619159990632457989L;

    @ApiModelProperty(value = "商品名称", required = true, position = 1)
    @NotBlank(message = "商品名称不能为空")
    private String name;

    @ApiModelProperty(value = "商品描述", position = 2)
    private String description;

    @ApiModelProperty(value = "商品单位", position = 3)
    @ValueInEnum(UnitEnum.class)
    private String unit;

    @ApiModelProperty(value = "商品重量", position = 4)
    private BigDecimal weight;

    @ApiModelProperty(value = "商品图片", position = 6)
    private String pic;

}
