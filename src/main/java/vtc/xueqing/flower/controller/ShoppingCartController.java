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
import vtc.xueqing.flower.vo.ShoppingCartVO;

import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;

// @Api(tags = "购物车对象功能接口")
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController extends BaseController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation("通过购物车ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<ShoppingCart> queryById(@PathVariable Long id){
        ShoppingCart byId = shoppingCartService.getById(id);
        return success(byId);
    }

    @ApiOperation("通过用户ID查询用户所有购物车")
    @GetMapping("list")
    public ResponseResult<List<ShoppingCartVO>> listByUserId(Long userId) {
        List<ShoppingCartVO> list = shoppingCartService.listShoppingCartWithProductInfo(userId);
        return success(list);
    }
    
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<ShoppingCartVO>> paginQuery(ShoppingCart shoppingCart,
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        Page<ShoppingCartVO> page = shoppingCartService.pageShoppingCartWithProductInfo(current, size, shoppingCart);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (page.getRecords() == null || page.getRecords().isEmpty()) {
            page.setRecords(Collections.emptyList());
        }
        
        return success(page);
    }

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public ResponseResult<ShoppingCart> addProductToCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart cart = shoppingCartService.addProductToCart(shoppingCart);
        return success(cart);
    }
    @ApiOperation("删除购物车信息根据id")
    @DeleteMapping("{id}")
    public ResponseResult removeById(@PathVariable Long id) {
        boolean b = shoppingCartService.removeById(id);
        return b ? success() : fail();
    }
}