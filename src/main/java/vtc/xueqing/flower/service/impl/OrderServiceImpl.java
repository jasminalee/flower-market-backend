package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.Order;
import vtc.xueqing.flower.mapper.OrderMapper;
import vtc.xueqing.flower.service.OrderService;

/**
 * 订单表;(order)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}