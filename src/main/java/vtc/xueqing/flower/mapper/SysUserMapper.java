package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.SysUser;

/**
 * 系统用户表;(sys_user)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {}