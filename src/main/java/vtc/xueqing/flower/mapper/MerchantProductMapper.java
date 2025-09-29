package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.MerchantProduct;

/**
 * 商户产品关联表;(merchant_product)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface MerchantProductMapper extends BaseMapper<MerchantProduct> {
}