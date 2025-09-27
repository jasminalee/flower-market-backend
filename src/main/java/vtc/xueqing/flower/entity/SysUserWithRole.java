package vtc.xueqing.flower.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户表(包含角色信息)
 * @author : Xueqing
 * @date : 2025-9-27
 */
@ApiModel("系统用户信息(包含角色信息)")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserWithRole extends SysUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    private Long roleId;
}