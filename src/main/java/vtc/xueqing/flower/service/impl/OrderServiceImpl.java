package vtc.xueqing.flower.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vtc.xueqing.flower.entity.Order;
import vtc.xueqing.flower.entity.OrderItem;
import vtc.xueqing.flower.mapper.OrderItemMapper;
import vtc.xueqing.flower.mapper.OrderMapper;
import vtc.xueqing.flower.service.OrderItemService;
import vtc.xueqing.flower.service.OrderService;
import vtc.xueqing.flower.vo.ShoppingCartVO;
import vtc.xueqing.flower.common.OrderNoGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单表;(order)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    
    @Autowired
    private OrderItemService orderItemService;
    
}