package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.Product;

/**
 * 产品信息表;(product)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}