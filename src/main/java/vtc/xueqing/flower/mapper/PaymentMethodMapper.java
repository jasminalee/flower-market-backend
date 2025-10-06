package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.PaymentMethod;

/**
 * 支付方式表;(payment_method)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface PaymentMethodMapper extends BaseMapper<PaymentMethod> {
}