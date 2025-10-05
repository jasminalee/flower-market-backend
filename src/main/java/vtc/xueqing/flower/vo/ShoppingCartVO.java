package vtc.xueqing.flower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("购物车信息VO")
public class ShoppingCartVO {

    @ApiModelProperty("购物车项ID")
    private Long id;

    @ApiModelProperty("用户ID（逻辑关联sys_user表）")
    private Long userId;

    @ApiModelProperty("产品ID（逻辑关联product表）")
    private Long productId;

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

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("SKU名称")
    private String skuName;

    @ApiModelProperty("商户名称")
    private String merchantName;

    @ApiModelProperty("主图")
    private String mainImage;

}