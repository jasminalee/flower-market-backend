package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.OrderItem;
import vtc.xueqing.flower.mapper.OrderItemMapper;
import vtc.xueqing.flower.service.OrderItemService;

/**
 * 订单明细表;(order_item)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
}