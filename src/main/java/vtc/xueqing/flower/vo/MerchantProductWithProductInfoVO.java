package vtc.xueqing.flower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vtc.xueqing.flower.entity.SysUser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商户产品VO类（包含产品信息和SKU信息）
 * 用于封装商户产品信息、商户信息、产品信息和SKU信息，方便前端展示
 */
@ApiModel("商户产品VO（包含产品信息和SKU信息）")
@Data
public class MerchantProductWithProductInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商户产品ID")
    private Long id;

    @ApiModelProperty("商户ID")
    private Long merchantId;

    // @ApiModelProperty("商户信息")
    // private SysUser merchant;

    @ApiModelProperty("产品ID")
    private Long productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品编码")
    private String productCode;

    @ApiModelProperty("SKU ID")
    private Long skuId;

    @ApiModelProperty("SKU名称")
    private String skuName;

    @ApiModelProperty("SKU编码")
    private String skuCode;

    @ApiModelProperty("商户定价")
    private BigDecimal price;

    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("产品描述")
    private String description;

    @ApiModelProperty("子图URL集合，JSON格式存储")
    private String subImages;

    @ApiModelProperty("产品详情")
    private String detail;

    @ApiModelProperty("商户库存")
    private Integer stock;

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

    @ApiModelProperty("状态（0-下架，1-上架）")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}