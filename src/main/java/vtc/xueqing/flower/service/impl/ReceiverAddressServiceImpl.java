package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.ReceiverAddress;
import vtc.xueqing.flower.mapper.ReceiverAddressMapper;
import vtc.xueqing.flower.service.ReceiverAddressService;

/**
 * 收货信息表;(receiver_address)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class ReceiverAddressServiceImpl extends ServiceImpl<ReceiverAddressMapper, ReceiverAddress> implements ReceiverAddressService {
}