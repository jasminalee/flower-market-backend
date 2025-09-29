package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.ProductSku;

/**
 * 产品SKU表;(product_sku)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface ProductSkuMapper extends BaseMapper<ProductSku> {
}