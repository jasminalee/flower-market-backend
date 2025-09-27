package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.mapper.SysUserMapper;
import vtc.xueqing.flower.service.SysUserService;

/**
 * 系统用户表;(sys_user)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {}