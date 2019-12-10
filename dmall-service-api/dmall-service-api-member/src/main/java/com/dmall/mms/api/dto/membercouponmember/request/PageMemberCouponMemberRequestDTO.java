package com.dmall.mms.api.dto.membercouponmember.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 会员-优惠券分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PageMemberCouponMemberRequestDTO", description="会员-优惠券分页请求实体")
public class PageMemberCouponMemberRequestDTO  extends PageRequestDTO {




    @ApiModelProperty(value = "优惠券id", position = 2)
    private String couponId;

    @ApiModelProperty(value = "会员id", position = 3)
    private String memberId;

    @ApiModelProperty(value = "使用状态 1-未使用;2-已使用;3-已过期", position = 4)
    private String useStatus;

    @ApiModelProperty(value = "获取类型 1-后台赠送;2-自己领取", position = 5)
    private String getType;

    @ApiModelProperty(value = "使用时间", position = 6)
    private Date useTime;

    @ApiModelProperty(value = "使用的订单号", position = 7)
    private String orderNo;






}