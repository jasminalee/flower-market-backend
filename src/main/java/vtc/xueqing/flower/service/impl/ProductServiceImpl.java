package vtc.xueqing.flower.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.Product;
import vtc.xueqing.flower.mapper.ProductMapper;
import vtc.xueqing.flower.service.ProductService;
import vtc.xueqing.flower.vo.ProductListVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品信息表;(product)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    
    @Override
    public List<ProductListVO> convertToProductListVO(List<Product> products) {
        return products.stream()
                .map(product -> {
                    ProductListVO vo = new ProductListVO();
                    BeanUtils.copyProperties(product, vo);
                    return vo;
                })
                .collect(Collectors.toList());
    }
}