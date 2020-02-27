package com.dmall.mms.api.dto.memberinvoice.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 会员发票分页请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageMemberInvoiceRequestDTO", description =  "会员发票分页请求实体")
public class PageMemberInvoiceRequestDTO  extends PageRequestDTO {



        @ApiModelProperty(value = "会员id", position = 2)
        private Long memberId;

        @ApiModelProperty(value = "发票抬头", position = 3)
        private String billHeader;

        @ApiModelProperty(value = "收票人姓名", position = 4)
        private String billReceiverName;

        @ApiModelProperty(value = "收票人电话", position = 5)
        private String billReceiverPhone;

        @ApiModelProperty(value = "收票人邮箱", position = 6)
        private String billReceiverEmail;







}
