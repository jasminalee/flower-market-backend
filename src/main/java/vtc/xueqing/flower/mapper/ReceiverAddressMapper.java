package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.ReceiverAddress;

/**
 * 收货信息表;(receiver_address)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface ReceiverAddressMapper extends BaseMapper<ReceiverAddress> {
}