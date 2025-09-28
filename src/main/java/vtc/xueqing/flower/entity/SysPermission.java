
package vtc.xueqing.flower.entity;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;

/**
 * 系统权限表
 * @author : xueqing li
 * @date : 2025-9-27
 */
@ApiModel("系统权限表")
@TableName("sys_permission")
@Data
@Builder
@Accessors(chain = true)
public class SysPermission implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("权限ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("父权限ID（用于构建权限树）")
    private Long parentId;
    @ApiModelProperty("权限名称")
    private String permissionName;
    @ApiModelProperty("权限编码")
    private String permissionCode;
    @ApiModelProperty("权限类型（1-菜单，2-按钮，3-接口）")
    private byte permissionType;
    @ApiModelProperty("资源路径（如URL）")
    private String url;
    @ApiModelProperty("请求方法（GET/POST等，适用于接口权限）")
    private String method;
    @ApiModelProperty("排序号")
    private Integer sort;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}