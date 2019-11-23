package com.dmall.mms.generator.dataobject;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员收藏sku表
 * </p>
 *
 * @author hang.yu
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mms_member_collection_sku")
public class MemberCollectionSkuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 会员id
     */
    @TableField("member_id")
    private Long memberId;

    /**
     * skuid
     */
    @TableField("sku_id")
    private Long skuId;

    /**
     * 商品id
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 商品编号
     */
    @TableField("product_no")
    private String productNo;

    /**
     * sku编号
     */
    @TableField("sku_no")
    private String skuNo;

    /**
     * 收藏时价格
     */
    private BigDecimal price;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created", fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 更新人
     */
    private Long modifier;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 状态 N-可用;Y-不可用
     */
    @TableField("is_deleted")
    private String isDeleted;


}
