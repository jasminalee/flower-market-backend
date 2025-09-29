package vtc.xueqing.flower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vtc.xueqing.flower.entity.MerchantProduct;
import vtc.xueqing.flower.entity.SysUser;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商户产品VO类
 * 用于封装商户产品信息及其商户信息，方便前端展示
 */
@ApiModel("商户产品VO")
@Data
public class MerchantProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商户产品ID")
    private Long id;

    @ApiModelProperty("商户ID")
    private Long merchantId;

    @ApiModelProperty("商户信息")
    private SysUser merchant;

    @ApiModelProperty("产品ID")
    private Long productId;

    @ApiModelProperty("SKU ID")
    private Long skuId;

    @ApiModelProperty("商户定价")
    private BigDecimal price;

    @ApiModelProperty("商户库存")
    private Integer stock;

    @ApiModelProperty("状态（0-下架，1-上架）")
    private Integer status;
}