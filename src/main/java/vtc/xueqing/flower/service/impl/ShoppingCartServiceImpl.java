package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.ShoppingCart;
import vtc.xueqing.flower.mapper.ShoppingCartMapper;
import vtc.xueqing.flower.service.ShoppingCartService;

/**
 * 购物车表;(shopping_cart)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}