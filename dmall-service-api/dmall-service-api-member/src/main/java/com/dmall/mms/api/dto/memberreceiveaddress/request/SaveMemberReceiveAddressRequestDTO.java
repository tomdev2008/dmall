package com.dmall.mms.api.dto.memberreceiveaddress.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.memberreceiveaddress.common.CommonMemberReceiveAddressRequestDTO;

/**
 * @description: 新增会员收货地址请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveMemberReceiveAddressRequestDTO", description = "新增会员收货地址请求实体")
public class SaveMemberReceiveAddressRequestDTO extends CommonMemberReceiveAddressRequestDTO {

}
