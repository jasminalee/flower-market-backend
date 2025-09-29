package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.Product;
import vtc.xueqing.flower.mapper.ProductMapper;
import vtc.xueqing.flower.service.ProductService;

/**
 * 产品信息表;(product)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}