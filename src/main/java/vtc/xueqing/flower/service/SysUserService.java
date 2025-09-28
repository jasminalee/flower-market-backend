package vtc.xueqing.flower.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    
    /**
     * 分页查询用户信息（包含角色ID）
     * @param page 分页参数
     * @param keyword 搜索关键词
     * @param status 用户状态
     * @return 分页结果
     */
    Page<SysUserWithRole> pageUsersWithRole(Page<SysUser> page, String keyword, Integer status);
}