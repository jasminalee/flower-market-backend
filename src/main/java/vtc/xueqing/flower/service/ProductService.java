package vtc.xueqing.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vtc.xueqing.flower.entity.Product;
import vtc.xueqing.flower.vo.ProductListVO;

import java.util.List;

/**
 * 产品信息表;(product)表服务接口
 *
 * @author : Xueqing
 */
public interface ProductService extends IService<Product> {
    
    /**
     * 将Product实体列表转换为ProductListVO列表
     * @param products Product实体列表
     * @return ProductListVO列表
     */
    List<ProductListVO> convertToProductListVO(List<Product> products);
}