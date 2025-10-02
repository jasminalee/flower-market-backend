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
import vtc.xueqing.flower.entity.ShoppingCart;
import vtc.xueqing.flower.service.ShoppingCartService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 购物车表;(shopping_cart)表控制层
 * @author : Xueqing
 */
@Api(tags = "购物车表对象功能接口")
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController extends BaseController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<ShoppingCart> queryById(
            @ApiParam("购物车项ID") 
            @NotNull(message = "购物车项ID不能为空") 
            @Min(value = 1, message = "购物车项ID必须大于0") 
            @PathVariable Long id){
        return success(shoppingCartService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<ShoppingCart>> paginQuery(ShoppingCart shoppingCart, 
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>(shoppingCart);
        return success(shoppingCartService.page(getPage(current, size), wrapper));
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<ShoppingCart>> list(ShoppingCart shoppingCart){
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>(shoppingCart);
        return success(shoppingCartService.list(wrapper));
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Valid ShoppingCart shoppingCart){
        return success(shoppingCartService.saveOrUpdate(shoppingCart));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(
            @ApiParam("购物车项ID") 
            @NotNull(message = "购物车项ID不能为空") 
            @Min(value = 1, message = "购物车项ID必须大于0") 
            @PathVariable Long id){
        return success(shoppingCartService.removeById(id));
    }
}