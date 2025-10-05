package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.ShoppingCart;

@Mapper // 标识为持久层 注入到spring容器中
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}
