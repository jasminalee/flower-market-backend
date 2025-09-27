
package vtc.xueqing.flower.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.SysPermission;
/**
 * 系统权限表;(sys_permission)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {}