package vtc.xueqing.flower.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-9-29
 * @desc : 通用评论表
 */
@ApiModel("通用评论信息")
@TableName("comment")
@Data
@Accessors(chain = true)
public class Comment implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("来源ID（可以是产品ID、文章ID等）")
    private Long sourceId;

    @ApiModelProperty("来源类型（product, article, forum等）")
    private String sourceType;

    @ApiModelProperty("用户ID（逻辑关联sys_user表）")
    private Long userId;

    @ApiModelProperty("父评论ID（用于构建评论树，0表示顶级评论）")
    private Long parentId;

    @ApiModelProperty("订单ID（逻辑关联order表，可为空，仅对产品评论有效）")
    private Long orderId;

    @ApiModelProperty("评分（1-5分，可为空）")
    private Integer rating;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("是否匿名（0-否，1-是）")
    private Integer isAnonymous;

    @ApiModelProperty("状态（0-隐藏，1-显示）")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}