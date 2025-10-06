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
 * @desc : 购物车表
 */
@Data   // 自动生成getter和setter方法
@ApiModel("购物车信息")
@TableName("shopping_cart")
public class ShoppingCart {
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("用户ID（逻辑关联sys_user表）")
    private Long userId;
    @ApiModelProperty("商品ID（逻辑关联MerchantProduct表）")
    private Long merchantProductId;
    @ApiModelProperty("SKU ID（逻辑关联product_sku表）")
    private Long skuId;
    @ApiModelProperty("商户ID（逻辑关联sys_user表）")
    private Long merchantId;
    @ApiModelProperty("数量")
    private Integer quantity;
    @ApiModelProperty("单价")
    private BigDecimal price;
    @ApiModelProperty("状态（0-无效，1-有效）")
    private Integer status;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}