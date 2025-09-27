
package vtc.xueqing.flower.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 用户角色关联表
 * @author : xueqing li
 * @date : 2025-9-27
 */
@ApiModel("用户角色关联表")
@TableName("sys_user_role")
@Data
public class SysUserRole implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("角色ID")
    private Long roleId;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}