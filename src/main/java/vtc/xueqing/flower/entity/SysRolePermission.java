
package vtc.xueqing.flower.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 角色权限关联表
 * @author : xueqing li
 * @date : 2025-9-28
 */
@ApiModel("角色权限关联表")
@TableName("sys_role_permission")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SysRolePermission implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("角色ID")
    private Long roleId;
    @ApiModelProperty("权限ID")
    private Long permissionId;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}