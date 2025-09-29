package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.ProductCategory;

/**
 * 产品分类表;(product_category)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
}