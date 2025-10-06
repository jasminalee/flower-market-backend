package vtc.xueqing.flower.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-9-25
 * @desc : 系统用户表
 */
@ApiModel("系统用户信息")
@TableName("sys_user")
@Data
@Accessors(chain = true)
public class SysUser implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户唯一标识")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("登录用户名")
    private String username;

    @ApiModelProperty("加密存储的密码")
    private String password;

    @ApiModelProperty("用户展示昵称")
    private String nickname;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户联系电话")
    private String phone;

    @ApiModelProperty("用户地址")
    private String addr;

    @ApiModelProperty("用户状态（0-禁用，1-正常）")
    private Integer status;

    @ApiModelProperty("用户头像URL")
    private String avatar;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}