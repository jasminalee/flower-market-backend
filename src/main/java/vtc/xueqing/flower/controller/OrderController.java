package vtc.xueqing.flower.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.Order;
import vtc.xueqing.flower.service.OrderService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * 订单表;(order)表控制层
 * @author : Xueqing
 */
// @Api(tags = "订单表对象功能接口")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<Order> queryById(
            @ApiParam("订单ID") 
            @NotNull(message = "订单ID不能为空") 
            @Min(value = 1, message = "订单ID必须大于0") 
            @PathVariable Long id){
        return success(orderService.getById(id));
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
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>(order);
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
            @ApiParam("收货人姓名") @RequestParam String receiverName,
            @ApiParam("收货人电话") @RequestParam String receiverPhone,
            @ApiParam("收货地址") @RequestParam String receiverAddress,
            @ApiParam("订单备注") @RequestParam(required = false) String remark) {
        
        Order order = orderService.createOrderFromDirectPurchase(userId, merchantProductId, quantity,
                receiverName, receiverPhone, receiverAddress, remark);
        
        return success(order);
    }
}