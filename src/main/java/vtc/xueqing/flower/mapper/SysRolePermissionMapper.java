
package vtc.xueqing.flower.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.SysRolePermission;
/**
 * 角色权限关联表;(sys_role_permission)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {}