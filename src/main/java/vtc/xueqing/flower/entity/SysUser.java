package vtc.xueqing.flower.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

 /**
 * @author : Xueqing
 * @date : 2025-9-25
 * @desc : 系统用户表
 */
@TableName("sys_user")
@Data
public class SysUser implements Serializable,Cloneable{
    /** 用户ID */
    @TableId(type = IdType.AUTO)
    private Long id ;
    /** 用户名(登陆) */
    private String username ;
    /** 密码(加密存储) */
    private String password ;
    /** 昵称 */
    private String nickname ;
    /** 邮箱 */
    private String email ;
    /** 手机号 */
    private String phone ;
    /** 地址 */
    private String addr ;
    /** 状态（0-禁用，1-正常） */
    private Integer status ;
    /** 创建时间 */
    private LocalDateTime createTime ;
    /** 更新时间 */
    private LocalDateTime updateTime ;

}