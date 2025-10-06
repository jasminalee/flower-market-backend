package vtc.xueqing.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vtc.xueqing.flower.entity.Order;

/**
 * 订单表;(order)表服务接口
 * @author : Xueqing
 */
public interface OrderService extends IService<Order> {
    /**
     * 直接购买商品生成订单
     * @param userId 用户ID
     * @param merchantProductId 商户产品ID
     * @param quantity 购买数量
     * @param receiverName 收货人姓名
     * @param receiverPhone 收货人电话
     * @param receiverAddress 收货地址
     * @param remark 订单备注
     * @return 生成的订单
     */
    Order createOrderFromDirectPurchase(Long userId, Long merchantProductId, Integer quantity,
                                       String receiverName, String receiverPhone, 
                                       String receiverAddress, String remark);
}