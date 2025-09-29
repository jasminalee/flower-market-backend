package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.MerchantProduct;
import vtc.xueqing.flower.mapper.MerchantProductMapper;
import vtc.xueqing.flower.service.MerchantProductService;

/**
 * 商户产品关联表;(merchant_product)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class MerchantProductServiceImpl extends ServiceImpl<MerchantProductMapper, MerchantProduct> implements MerchantProductService {
}