package com.dmall.pms.mock.api.dto.product.response;

import com.dmall.mms.api.dto.member.response.MemberResponseDTO;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 商品响应实体
 * @author: created by hang.yu on 2019/10/14 21:52
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ProductResponseDTO implements Serializable {

    private static final long serialVersionUID = -408786531712159668L;

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 会员对象
     */
    private MemberResponseDTO memberResponseDTO;

}
