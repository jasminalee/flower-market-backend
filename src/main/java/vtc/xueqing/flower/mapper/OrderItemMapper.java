package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.OrderItem;

/**
 * 订单明细表;(order_item)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}