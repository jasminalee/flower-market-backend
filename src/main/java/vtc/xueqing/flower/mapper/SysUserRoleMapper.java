
package vtc.xueqing.flower.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.SysUserRole;
/**
 * 用户角色关联表;(sys_user_role)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {}