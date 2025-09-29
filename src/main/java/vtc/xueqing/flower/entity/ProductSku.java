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
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-9-29
 * @desc : 产品SKU表
 */
@ApiModel("产品SKU信息")
@TableName("product_sku")
@Data
@Accessors(chain = true)
public class ProductSku implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("SKU ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("产品ID（逻辑关联product表）")
    private Long productId;

    @ApiModelProperty("SKU名称（如：红色-L）")
    @NotBlank(message = "SKU名称不能为空")
    private String skuName;

    @ApiModelProperty("SKU编码")
    @NotBlank(message = "SKU编码不能为空")
    private String skuCode;

    @ApiModelProperty("价格")
    @NotNull(message = "价格不能为空")
    @Positive(message = "价格必须大于0")
    private BigDecimal price;

    @ApiModelProperty("库存数量")
    @NotNull(message = "库存不能为空")
    @PositiveOrZero(message = "库存必须大于等于0")
    private Integer stock;

    @ApiModelProperty("规格描述，JSON格式存储")
    private String specifications;

    @ApiModelProperty("状态（0-无效，1-有效）")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}