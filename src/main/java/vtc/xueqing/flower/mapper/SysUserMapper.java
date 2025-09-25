package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vtc.xueqing.flower.entity.SysUser;

 /**
 * 系统用户表;(sys_user)表数据库访问层
 * @author : Xueqing
 * @date : 2025-9-25
 */
@Mapper
public interface SysUserMapper  extends BaseMapper<SysUser>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<SysUser> selectByPage(IPage<SysUser> page , @Param(Constants.WRAPPER) Wrapper<SysUser> wrapper);
}