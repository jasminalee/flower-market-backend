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
import javax.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-9-29
 * @desc : 产品分类表
 */
@ApiModel("产品分类信息")
@TableName("product_category")
@Data
@Accessors(chain = true)
public class ProductCategory implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("分类名称")
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    @ApiModelProperty("父分类ID（用于构建分类树）")
    private Long parentId;

    @ApiModelProperty("分类级别（1-一级分类，2-二级分类，3-三级分类）")
    @NotNull(message = "分类级别不能为空")
    private Integer categoryLevel;

    @ApiModelProperty("排序号")
    @PositiveOrZero(message = "排序号必须大于等于0")
    private Integer sort;

    @ApiModelProperty("状态（0-禁用，1-正常）")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}