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
 * @date : 2025-9-29
 * @desc : 商户产品关联表
 */
@ApiModel("商户产品信息")
@TableName("merchant_product")
@Data
@Accessors(chain = true)
public class MerchantProduct implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商户ID（逻辑关联sys_user表）")
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @ApiModelProperty("产品ID（逻辑关联product表）")
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @ApiModelProperty("SKU ID（逻辑关联product_sku表）")
    @NotNull(message = "SKU ID不能为空")
    private Long skuId;

    @ApiModelProperty("商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String merchantName;

    @ApiModelProperty("商户定价")
    @NotNull(message = "商户定价不能为空")
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