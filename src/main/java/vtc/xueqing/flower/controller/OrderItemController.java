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
import vtc.xueqing.flower.entity.OrderItem;
import vtc.xueqing.flower.service.OrderItemService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 订单明细表;(order_item)表控制层
 * @author : Xueqing
 */
@Api(tags = "订单明细表对象功能接口")
@RestController
@RequestMapping("/orderItem")
public class OrderItemController extends BaseController {
    @Autowired
    private OrderItemService orderItemService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<OrderItem> queryById(
            @ApiParam("订单明细ID") 
            @NotNull(message = "订单明细ID不能为空") 
            @Min(value = 1, message = "订单明细ID必须大于0") 
            @PathVariable Long id){
        return success(orderItemService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<OrderItem>> paginQuery(OrderItem orderItem, 
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>(orderItem);
        return success(orderItemService.page(getPage(current, size), wrapper));
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<OrderItem>> list(OrderItem orderItem){
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>(orderItem);
        return success(orderItemService.list(wrapper));
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Valid OrderItem orderItem){
        return success(orderItemService.saveOrUpdate(orderItem));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(
            @ApiParam("订单明细ID") 
            @NotNull(message = "订单明细ID不能为空") 
            @Min(value = 1, message = "订单明细ID必须大于0") 
            @PathVariable Long id){
        return success(orderItemService.removeById(id));
    }
}