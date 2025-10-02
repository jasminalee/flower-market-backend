package vtc.xueqing.flower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-10-02
 * @desc : 商品列表VO
 */
@ApiModel("商品列表信息")
@Data
public class ProductListVO {

    @ApiModelProperty("产品ID")
    private Long id;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品编码")
    private String productCode;

    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("产品描述")
    private String description;

    @ApiModelProperty("主图URL")
    private String mainImage;

    @ApiModelProperty("产品类型（1-花卉，2-第三方产品）")
    private Integer productType;

    @ApiModelProperty("状态（0-下架，1-上架）")
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
}