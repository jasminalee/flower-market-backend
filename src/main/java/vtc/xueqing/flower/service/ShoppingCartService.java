package vtc.xueqing.flower.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vtc.xueqing.flower.entity.ShoppingCart;
import vtc.xueqing.flower.vo.ShoppingCartVO;

import java.util.List;

public interface ShoppingCartService extends IService<ShoppingCart> {
    List<ShoppingCartVO> listShoppingCartWithProductInfo(Long userId);
    
    Page<ShoppingCartVO> pageShoppingCartWithProductInfo(Long current, Long size, ShoppingCart shoppingCart);
    
    /**
     * 添加商品到购物车
     * 如果同一商户的同一商品已在购物车中，则数量加1
     * 否则新增一条购物车记录
     * @param shoppingCart 购物车信息
     * @return 添加后的购物车记录
     */
    ShoppingCart addProductToCart(ShoppingCart shoppingCart);
}