package vtc.xueqing.flower.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.ShoppingCart;
import vtc.xueqing.flower.service.ShoppingCartService;

import java.util.List;

@Api(tags = "购物车对象功能接口")
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
    public ResponseResult<List<ShoppingCart>> listByUserId(Long userId) {
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, userId);
        List<ShoppingCart> list = shoppingCartService.list(wrapper);
        return success(list);

    }

}
