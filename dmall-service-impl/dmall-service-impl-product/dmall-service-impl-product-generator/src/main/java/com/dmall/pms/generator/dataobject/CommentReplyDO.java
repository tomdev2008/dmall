package com.dmall.pms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评价回复表
 * </p>
 *
 * @author yuhang
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_comment_reply")
public class CommentReplyDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评价id
     */
    private Long commentId;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 内容
     */
    private String content;

    /**
     * 会员昵称
     */
    private String memberNickName;

    /**
     * 会员头像
     */
    private String memberIcon;

    /**
     * 回复类型 1-会员;2-本人;3-管理员
     */
    private Integer type;

    /**
     * 点赞数
     */
    private String praseCount;

    /**
     * 被回复人昵称
     */
    private String nickName;

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
     * 状态 Y-可用;N-不可用
     */
    private String isDeleted;


}