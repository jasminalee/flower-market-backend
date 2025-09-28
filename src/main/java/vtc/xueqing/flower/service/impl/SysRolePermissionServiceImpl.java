package vtc.xueqing.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.SysRolePermission;
import vtc.xueqing.flower.mapper.SysRolePermissionMapper;
import vtc.xueqing.flower.service.SysRolePermissionService;

/**
 * 角色权限关联表;(sys_role_permission)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {
    
    @Override
    public boolean removeByPermissionId(Long permissionId) {
        LambdaQueryWrapper<SysRolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRolePermission::getPermissionId, permissionId);
        return this.remove(wrapper);
    }
}