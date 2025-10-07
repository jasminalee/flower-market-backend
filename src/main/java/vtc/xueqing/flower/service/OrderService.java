package vtc.xueqing.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vtc.xueqing.flower.entity.Order;
import vtc.xueqing.flower.vo.OrderDetailVO;

/**
 * 订单表;(order)表服务接口
 * @author : Xueqing
 */
public interface OrderService extends IService<Order> {

    /**
     * 直接购买商品生成订单（使用收货地址ID）
     * @param userId 用户ID
     * @param merchantProductId 商户产品ID
     * @param quantity 购买数量
     * @param receiverAddressId 收货地址ID
     * @return 生成的订单
     */
    Order createOrderFromDirectPurchase(Long userId, Long merchantProductId, Integer quantity,
                                       Long receiverAddressId);
    
    /**
     * 根据订单ID查询订单详情（包含订单项）
     * @param orderId 订单ID
     * @return 订单详情VO
     */
    OrderDetailVO getOrderDetail(Long orderId);
}