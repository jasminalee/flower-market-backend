package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.Order;

/**
 * 订单表;(order)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}