package vtc.xueqing.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vtc.xueqing.flower.entity.SysRolePermission;

/**
 * 角色权限关联表;(sys_role_permission)表服务接口
 *
 * @author : Xueqing
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {
    
    /**
     * 根据权限ID删除关联关系
     * @param permissionId 权限ID
     * @return 是否删除成功
     */
    boolean removeByPermissionId(Long permissionId);
}