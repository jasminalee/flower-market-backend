
package vtc.xueqing.flower.service.impl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.SysPermission;
import vtc.xueqing.flower.mapper.SysPermissionMapper;
import vtc.xueqing.flower.service.SysPermissionService;
/**
 * 系统权限表;(sys_permission)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {}