package vtc.xueqing.flower.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-10-07
 * @desc : 论坛帖子表
 */
@ApiModel("论坛帖子信息")
@TableName("forum_post")
@Data
@Accessors(chain = true)
public class ForumPost implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("帖子ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("板块ID（逻辑关联forum_category表）")
    @NotNull(message = "板块ID不能为空")
    private Long categoryId;

    @ApiModelProperty("用户ID（逻辑关联sys_user表）")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty("帖子标题")
    @NotBlank(message = "帖子标题不能为空")
    private String title;

    @ApiModelProperty("帖子内容")
    private String content;

    @ApiModelProperty("帖子类型（1-普通文本，2-视频）")
    @NotNull(message = "帖子类型不能为空")
    private Integer postType;

    @ApiModelProperty("视频URL（仅当post_type为2时有效）")
    private String videoUrl;

    @ApiModelProperty("视频封面图片URL")
    private String coverImage;

    @ApiModelProperty("浏览次数")
    private Integer viewCount;

    @ApiModelProperty("点赞次数")
    private Integer likeCount;

    @ApiModelProperty("收藏次数")
    private Integer favoriteCount;

    @ApiModelProperty("评论次数")
    private Integer commentCount;

    @ApiModelProperty("是否置顶（0-否，1-是）")
    private Integer isTop;

    @ApiModelProperty("是否精华（0-否，1-是）")
    private Integer isEssence;

    @ApiModelProperty("状态（0-删除，1-正常，2-审核中）")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}