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
 * @date : 2025-10-02
 * @desc : 订单明细表
 */
@ApiModel("订单明细信息")
@TableName("order_item")
@Data
@Accessors(chain = true)
public class OrderItem implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单明细ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单ID（逻辑关联order表）")
    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    @ApiModelProperty("产品ID（逻辑关联product表）")
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @ApiModelProperty("SKU ID（逻辑关联product_sku表）")
    @NotNull(message = "SKU ID不能为空")
    private Long skuId;

    @ApiModelProperty("商户ID（逻辑关联sys_user表）")
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @ApiModelProperty("产品名称")
    @NotBlank(message = "产品名称不能为空")
    private String productName;

    @ApiModelProperty("SKU名称")
    @NotBlank(message = "SKU名称不能为空")
    private String skuName;

    @ApiModelProperty("单价")
    @NotNull(message = "单价不能为空")
    private BigDecimal price;

    @ApiModelProperty("数量")
    @NotNull(message = "数量不能为空")
    private Integer quantity;

    @ApiModelProperty("总价")
    @NotNull(message = "总价不能为空")
    private BigDecimal totalPrice;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}