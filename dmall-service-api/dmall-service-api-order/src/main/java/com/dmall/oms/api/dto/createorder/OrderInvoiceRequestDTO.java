package com.dmall.oms.api.dto.createorder;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.oms.api.enums.InvoiceContentEnum;
import com.dmall.oms.api.enums.InvoiceHeaderTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 发票实体
 * @author: created by hang.yu on 2020/3/26 22:51
 */
@Data
@ApiModel(value = "OrderInvoiceRequestDTO", description = "订单发票实体")
public class OrderInvoiceRequestDTO implements Serializable {

    private static final long serialVersionUID = -1480167265648146117L;

    @ApiModelProperty(value = "发票id", required = true, position = 1)
    @NotNull(message = "发票id不能为空")
    private Long id;

    @ApiModelProperty(value = "发票抬头 1-个人;2-单位", required = true, position = 2)
    @NotNull(message = "发票抬头不能为空")
    @ValueInEnum(InvoiceHeaderTypeEnum.class)
    private Integer invoiceHeader;

    @ApiModelProperty(value = "个人名称", position = 3)
    private String personalName;

    @ApiModelProperty(value = "收票人手机号", position = 4)
    private String receiverPhone;

    @ApiModelProperty(value = "收票人邮箱", position = 5)
    private String receiverEmail;

    @ApiModelProperty(value = "单位名称", position = 6)
    private String companyName;

    @ApiModelProperty(value = "纳税人识别号", position = 7)
    private String customerTaxNumber;

    @ApiModelProperty(value = "发票内容 1-商品类别;2-商品明细", required = true, position = 8)
    @NotNull(message = "发票内容不能为空")
    @ValueInEnum(InvoiceContentEnum.class)
    private Integer invoiceContent;

}
