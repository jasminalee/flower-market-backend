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
 * @date : 2025-10-02
 * @desc : 购物车表
 */
@ApiModel("购物车信息")
@TableName("shopping_cart")
@Data
@Accessors(chain = true)
public class ShoppingCart implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("购物车项ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户ID（逻辑关联sys_user表）")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty("产品ID（逻辑关联product表）")
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @ApiModelProperty("SKU ID（逻辑关联product_sku表）")
    @NotNull(message = "SKU ID不能为空")
    private Long skuId;

    @ApiModelProperty("商户ID（逻辑关联sys_user表）")
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @ApiModelProperty("数量")
    @NotNull(message = "数量不能为空")
    private Integer quantity;

    @ApiModelProperty("状态（0-无效，1-有效）")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}