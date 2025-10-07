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
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-10-07
 * @desc : 论坛板块表
 */
@ApiModel("论坛板块信息")
@TableName("forum_category")
@Data
@Accessors(chain = true)
public class ForumCategory implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("板块ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("板块名称")
    @NotBlank(message = "板块名称不能为空")
    private String name;

    @ApiModelProperty("板块描述")
    private String description;

    @ApiModelProperty("父板块ID（用于构建板块树，0表示顶级板块）")
    private Long parentId;

    @ApiModelProperty("板块级别（1-一级板块，2-二级板块）")
    @NotNull(message = "板块级别不能为空")
    private Integer level;

    @ApiModelProperty("排序号")
    private Integer sort;

    @ApiModelProperty("状态（0-禁用，1-启用）")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("板块图标URL")
    private String icon;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}