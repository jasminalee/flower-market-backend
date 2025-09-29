package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.ProductSku;
import vtc.xueqing.flower.mapper.ProductSkuMapper;
import vtc.xueqing.flower.service.ProductSkuService;

/**
 * 产品SKU表;(product_sku)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {
}