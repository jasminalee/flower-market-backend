package vtc.xueqing.flower.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vtc.xueqing.flower.entity.ShoppingCart;
import vtc.xueqing.flower.mapper.ShoppingCartMapper;
import vtc.xueqing.flower.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
