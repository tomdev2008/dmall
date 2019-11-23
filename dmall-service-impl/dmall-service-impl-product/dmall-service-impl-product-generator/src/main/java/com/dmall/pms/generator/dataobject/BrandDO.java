package com.dmall.pms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author hang.yu
 * @since 2019-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_brand")
public class BrandDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField(whereStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    /**
     * 英文名称
     */
    @TableField(whereStrategy = FieldStrategy.NOT_EMPTY)
    private String englishName;

    /**
     * 首字母
     */
    @TableField(whereStrategy = FieldStrategy.NOT_EMPTY)
    private String firstLetter;

    /**
     * logo
     */
    private String logo;

    /**
     * 品牌大图
     */
    private String bigPic;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 更新人
     */
    private Long modifier;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 状态 N-可用;Y-不可用
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private String isDeleted;


}
