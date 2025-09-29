package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.ProductCategory;
import vtc.xueqing.flower.mapper.ProductCategoryMapper;
import vtc.xueqing.flower.service.ProductCategoryService;

/**
 * 产品分类表;(product_category)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
}