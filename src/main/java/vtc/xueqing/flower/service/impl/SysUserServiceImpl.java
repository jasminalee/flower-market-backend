package vtc.xueqing.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.entity.SysUserWithRole;
import vtc.xueqing.flower.entity.SysUserRole;
import vtc.xueqing.flower.mapper.SysUserMapper;
import vtc.xueqing.flower.service.SysUserService;
import vtc.xueqing.flower.service.SysUserRoleService;
import java.time.LocalDateTime;

/**
 * 系统用户表;(sys_user)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Override
    public boolean saveOrUpdateWithRole(SysUserWithRole sysUserWithRole) {
        // 保存或更新用户信息
        // 注意：saveOrUpdate方法在新增用户时会自动将生成的ID设置回sysUserWithRole对象中
        boolean saved = this.saveOrUpdate(sysUserWithRole);
        
        if (saved && sysUserWithRole.getRoleId() != null) {
            // 创建或更新用户角色关联
            SysUserRole sysUserRole = SysUserRole.builder()
                    .userId(sysUserWithRole.getId())
                    .roleId(sysUserWithRole.getId())
                    .build();
            // 保存或更新用户角色关联
            sysUserRoleService.saveOrUpdate(sysUserRole, new LambdaQueryWrapper<>(sysUserRole));
        }
        return saved;
    }
}