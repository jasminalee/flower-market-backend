
package vtc.xueqing.flower.service.impl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.SysRole;
import vtc.xueqing.flower.mapper.SysRoleMapper;
import vtc.xueqing.flower.service.SysRoleService;
/**
 * 系统角色表;(sys_role)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {}