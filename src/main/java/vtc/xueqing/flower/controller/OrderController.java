package vtc.xueqing.flower.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.common.OrderNoGenerator;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.*;
import vtc.xueqing.flower.service.*;
import vtc.xueqing.flower.vo.OrderDetailVO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 订单表;(order)表控制层
 * @author : Xueqing
 */
@Api(tags = "订单表对象功能接口")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController extends BaseController {
    private final OrderService orderService;
    private final MerchantProductService merchantProductService;
    private final ReceiverAddressService receiverAddressService;
    private final OrderItemService orderItemService;
    private final ProductSkuService productSkuService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<Order> queryById(
            @ApiParam("订单ID") 
            @NotNull(message = "订单ID不能为空") 
            @Min(value = 1, message = "订单ID必须大于0") 
            @PathVariable Long id){
        return success(orderService.getById(id));
    }
    
    @ApiOperation("通过ID查询订单详情（包含订单项）")
    @GetMapping("/{id}/detail")
    public ResponseResult<OrderDetailVO> getOrderDetail(
            @ApiParam("订单ID") 
            @NotNull(message = "订单ID不能为空") 
            @Min(value = 1, message = "订单ID必须大于0") 
            @PathVariable Long id){
        OrderDetailVO orderDetail = orderService.getOrderDetail(id);
        return success(orderDetail);
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<Order>> paginQuery(Order order, 
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>(order).orderByDesc(Order::getUpdateTime);
        Page<Order> pageResult = orderService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (pageResult.getRecords() == null || pageResult.getRecords().isEmpty()) {
            pageResult.setRecords(Collections.emptyList());
        }
        
        return success(pageResult);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<Order>> list(Order order){
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>(order);
        return success(orderService.list(wrapper));
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Valid Order order){
        return success(orderService.saveOrUpdate(order));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(
            @ApiParam("订单ID") 
            @NotNull(message = "订单ID不能为空") 
            @Min(value = 1, message = "订单ID必须大于0") 
            @PathVariable Long id){
        return success(orderService.removeById(id));
    }
    
    @ApiOperation("直接购买生成订单")
    @PostMapping("/createOrderFromDirectPurchase")
    public ResponseResult<Order> createOrderFromDirectPurchase(
            @ApiParam("用户ID") @RequestParam Long userId,
            @ApiParam("商户产品ID") @RequestParam Long merchantProductId,
            @ApiParam("购买数量") @RequestParam Integer quantity,
            @ApiParam("收货信息ID") @RequestParam Long receiverAddressId) {
        
        Order order = orderService.createOrderFromDirectPurchase(userId, merchantProductId, quantity, receiverAddressId);
        
        return success(order);
    }
    @ApiOperation("购物车结账")
    @PostMapping("/cartPurchase")
    public ResponseResult<Order> createOrderByCartPurchase(
            @RequestBody List<ShoppingCart> shoppingCarts,
            @ApiParam("用户ID") @RequestParam Long userId,
            @ApiParam("备注") @RequestParam(required = false) String remark,
            @ApiParam("收货信息ID") @RequestParam(required = false) Long receiverAddressId) {
        Order order = cartPurchase(shoppingCarts, userId, remark, receiverAddressId);
        return success(order);
    }

    private Order cartPurchase(
            List<ShoppingCart> shoppingCarts,
            Long userId,
            String remark,
            Long receiverAddressId) {
        ReceiverAddress receiverAddress = new ReceiverAddress();
        if (Objects.nonNull(receiverAddressId))
            receiverAddress = receiverAddressService.getById(receiverAddressId);

        List<Long> collect = shoppingCarts.stream().map(ShoppingCart::getMerchantProductId).collect(Collectors.toList());
        BigDecimal totalAmount = shoppingCarts.stream()
                .map(cart ->
                        cart.getPrice().multiply(new BigDecimal(cart.getQuantity()))
                ).reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<Long, ShoppingCart> cartMap = shoppingCarts.stream().collect(Collectors.toMap(ShoppingCart::getMerchantProductId, shoppingCart -> shoppingCart));
        List<MerchantProduct> merchantProducts = merchantProductService.listByIds(collect);
        List<Long> skuIds = merchantProducts.stream().map(MerchantProduct::getSkuId).collect(Collectors.toList());
        List<ProductSku> productSkus = productSkuService.listByIds(skuIds);
        Map<Long, ProductSku> skuMap = productSkus.stream().collect(Collectors.toMap(ProductSku::getId, sku -> sku));

        Order order = new Order();
        String orderNo = OrderNoGenerator.generateOrderNo();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setDiscountAmount(BigDecimal.ZERO);
        order.setPayAmount(totalAmount);
        order.setStatus(1); // 待付款状态
        order.setReceiverName(receiverAddress.getReceiverName());
        order.setReceiverPhone(receiverAddress.getReceiverPhone());
        order.setReceiverAddress(receiverAddress.getDetailAddress());
        order.setRemark(remark);

        // 先保存订单，获取生成的订单ID
        orderService.save(order);
        
        List<OrderItem> orderItems = merchantProducts.stream().map(merchantProduct -> {
            OrderItem orderItem = new OrderItem();
            BeanUtil.copyProperties(merchantProduct, orderItem);
            orderItem.setQuantity(cartMap.get(merchantProduct.getId()).getQuantity());
            orderItem.setProductName(merchantProduct.getMerchantName());
            orderItem.setSkuName(skuMap.get(merchantProduct.getSkuId()).getSkuName());
            // 使用已保存订单的ID
            orderItem.setOrderId(order.getId());
            // 计算总价
            orderItem.setTotalPrice(orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
            return orderItem;
        }).collect(Collectors.toList());
        orderItemService.saveOrUpdateBatch(orderItems);
        return order;
    }
}