package vtc.xueqing.flower.service.impl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.SysPermission;
import vtc.xueqing.flower.mapper.SysPermissionMapper;
import vtc.xueqing.flower.service.SysPermissionService;
import vtc.xueqing.flower.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统权限表;(sys_permission)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Long id) {
        // 先删除角色权限关联关系
        sysRolePermissionService.removeByPermissionId(id);
        // 再删除权限本身
        return super.removeById(id);
    }
}