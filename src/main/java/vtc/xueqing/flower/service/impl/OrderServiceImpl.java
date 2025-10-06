package vtc.xueqing.flower.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vtc.xueqing.flower.entity.MerchantProduct;
import vtc.xueqing.flower.entity.Order;
import vtc.xueqing.flower.entity.OrderItem;
import vtc.xueqing.flower.entity.Product;
import vtc.xueqing.flower.entity.ProductSku;
import vtc.xueqing.flower.entity.ReceiverAddress;
import vtc.xueqing.flower.mapper.OrderMapper;
import vtc.xueqing.flower.service.MerchantProductService;
import vtc.xueqing.flower.service.OrderItemService;
import vtc.xueqing.flower.service.OrderService;
import vtc.xueqing.flower.service.ProductService;
import vtc.xueqing.flower.service.ProductSkuService;
import vtc.xueqing.flower.service.ReceiverAddressService;
import vtc.xueqing.flower.common.OrderNoGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表;(order)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    
    @Autowired
    private OrderItemService orderItemService;
    
    @Autowired
    private MerchantProductService merchantProductService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductSkuService productSkuService;
    
    @Autowired
    private ReceiverAddressService receiverAddressService;
    
    @Override
    @Transactional
    public Order createOrderFromDirectPurchase(Long userId, Long merchantProductId, Integer quantity,
                                              String receiverName, String receiverPhone, 
                                              String receiverAddress, String remark) {
        // 获取商户产品信息
        MerchantProduct merchantProduct = merchantProductService.getById(merchantProductId);
        if (merchantProduct == null) {
            throw new RuntimeException("商户产品不存在");
        }
        
        // 检查库存
        if (merchantProduct.getStock() < quantity) {
            throw new RuntimeException("库存不足");
        }
        
        // 获取产品和SKU信息
        Product product = productService.getById(merchantProduct.getProductId());
        ProductSku productSku = productSkuService.getById(merchantProduct.getSkuId());
        
        // 计算订单金额
        BigDecimal totalAmount = merchantProduct.getPrice().multiply(new BigDecimal(quantity));
        
        // 创建订单主表
        Order order = new Order();
        order.setOrderNo(OrderNoGenerator.generateOrderNo());
        order.setUserId(userId);
        order.setMerchantId(merchantProduct.getMerchantId());
        order.setTotalAmount(totalAmount);
        order.setDiscountAmount(BigDecimal.ZERO);
        order.setPayAmount(totalAmount);
        order.setStatus(1); // 待付款状态
        order.setReceiverName(receiverName);
        order.setReceiverPhone(receiverPhone);
        order.setReceiverAddress(receiverAddress);
        order.setRemark(remark);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        
        // 保存订单主表
        this.save(order);
        
        // 创建订单明细
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());
        orderItem.setProductId(merchantProduct.getProductId());
        orderItem.setSkuId(merchantProduct.getSkuId());
        orderItem.setMerchantId(merchantProduct.getMerchantId());
        orderItem.setProductName(product.getProductName());
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setPrice(merchantProduct.getPrice());
        orderItem.setQuantity(quantity);
        orderItem.setTotalPrice(totalAmount);
        orderItem.setCreateTime(LocalDateTime.now());
        orderItem.setUpdateTime(LocalDateTime.now());
        
        // 保存订单明细
        orderItemService.save(orderItem);
        
        return order;
    }
    
    @Override
    @Transactional
    public Order createOrderFromDirectPurchase(Long userId, Long merchantProductId, Integer quantity,
                                              Long receiverAddressId) {
        // 获取收货地址信息
        ReceiverAddress receiverAddress = receiverAddressService.getById(receiverAddressId);
        if (receiverAddress == null) {
            throw new RuntimeException("收货地址不存在");
        }
        
        // 拼接完整地址
        StringBuilder fullAddress = new StringBuilder();
        fullAddress.append(receiverAddress.getProvince())
                  .append(receiverAddress.getCity());
        if (receiverAddress.getDistrict() != null && !receiverAddress.getDistrict().isEmpty()) {
            fullAddress.append(receiverAddress.getDistrict());
        }
        fullAddress.append(receiverAddress.getDetailAddress());
        
        // 调用原有的购买方法
        return createOrderFromDirectPurchase(userId, merchantProductId, quantity,
                receiverAddress.getReceiverName(), receiverAddress.getReceiverPhone(),
                fullAddress.toString(), "");
    }
}