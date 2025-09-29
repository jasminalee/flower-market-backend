package vtc.xueqing.flower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vtc.xueqing.flower.entity.Product;
import vtc.xueqing.flower.entity.ProductSku;

import java.io.Serializable;
import java.util.List;

/**
 * 产品详情VO类
 * 用于封装产品详情及其SKU信息，方便前端使用
 */
@ApiModel("产品详情VO")
@Data
public class ProductDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("产品基本信息")
    private Product product;

    @ApiModelProperty("产品SKU列表")
    private List<ProductSku> skus;
}