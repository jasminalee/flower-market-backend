
package vtc.xueqing.flower.service.impl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.SysUserRole;
import vtc.xueqing.flower.mapper.SysUserRoleMapper;
import vtc.xueqing.flower.service.SysUserRoleService;
/**
 * 用户角色关联表;(sys_user_role)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {}