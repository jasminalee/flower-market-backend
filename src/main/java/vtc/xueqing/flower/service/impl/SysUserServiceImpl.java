package vtc.xueqing.flower.service.impl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.mapper.SysUserMapper;
import vtc.xueqing.flower.service.SysUserService;

/**
 * 系统用户表;(sys_user)表服务实现类
 *
 * @author : Xueqing
 * @date : 2025-9-25
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}