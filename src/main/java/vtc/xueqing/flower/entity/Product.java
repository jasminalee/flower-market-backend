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
import java.math.BigDecimal;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-9-29
 * @desc : 产品信息表
 */
@ApiModel("产品信息")
@TableName("product")
@Data
@Accessors(chain = true)
public class Product implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("产品ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("产品名称")
    @NotBlank(message = "产品名称不能为空")
    private String productName;

    @ApiModelProperty("产品编码")
    @NotBlank(message = "产品编码不能为空")
    private String productCode;

    @ApiModelProperty("分类ID（逻辑关联product_category表）")
    @NotNull(message = "分类ID不能为空")
    private Long categoryId;

    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("产品描述")
    private String description;

    @ApiModelProperty("主图URL")
    private String mainImage;

    @ApiModelProperty("子图URL集合，JSON格式存储")
    private String subImages;

    @ApiModelProperty("产品详情")
    private String detail;

    @ApiModelProperty("产品类型（1-花卉，2-第三方产品）")
    @NotNull(message = "产品类型不能为空")
    private Integer productType;

    @ApiModelProperty("状态（0-下架，1-上架）")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("平均评分")
    private BigDecimal avgRating;

    @ApiModelProperty("总销量")
    private Integer totalSales;

    @ApiModelProperty("最低价格")
    private BigDecimal minPrice;

    @ApiModelProperty("是否热销(1:是,0:否)")
    private Integer isHot;

    @ApiModelProperty("是否打折(1:是,0:否)")
    private Integer isDiscounted;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}