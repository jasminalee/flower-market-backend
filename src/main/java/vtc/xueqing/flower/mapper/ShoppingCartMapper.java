package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.ShoppingCart;

/**
 * 购物车表;(shopping_cart)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}