package com.dmall.pms.api.dto.product.response.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 销售规格响应实体
 * @author: created by hang.yu on 2019/12/26 22:29
 */
@Data
@ApiModel(value = "SpecificationsResponseDTO", description = "销售规格响应实体")
public class SpecificationsResponseDTO implements Serializable {

    private static final long serialVersionUID = -4713638581805218367L;

    @ApiModelProperty(value = "属性id", position = 1)
    @NotNull(message = "属性id不能为空")
    private Long attributeId;

    @ApiModelProperty(value = "商品属性值列表", position = 2)
    private List<ProductAttributeValueResponseDTO> specificationsValues;

}
