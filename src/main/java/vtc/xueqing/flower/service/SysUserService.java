package vtc.xueqing.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.entity.SysUserWithRole;
import vtc.xueqing.flower.service.SysUserRoleService;

/**
 * 系统用户表;(sys_user)表服务接口
 *
 * @author : Xueqing
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 保存或更新用户信息及角色关联信息
     * @param sysUserWithRole 用户及角色信息
     * @return 是否成功
     */
    boolean saveOrUpdateWithRole(SysUserWithRole sysUserWithRole);
}