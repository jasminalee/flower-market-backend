
package vtc.xueqing.flower.service.impl;
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
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {}