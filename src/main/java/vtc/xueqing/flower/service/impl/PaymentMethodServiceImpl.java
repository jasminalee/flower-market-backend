package vtc.xueqing.flower.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.mapper.PaymentMethodMapper;
import vtc.xueqing.flower.entity.PaymentMethod;
import vtc.xueqing.flower.service.PaymentMethodService;
import org.springframework.stereotype.Service;

/**
 * 支付方式表;(payment_method)表服务实现类
 * @author : Xueqing
 */
@Service
public class PaymentMethodServiceImpl extends ServiceImpl<PaymentMethodMapper, PaymentMethod> implements PaymentMethodService {
}