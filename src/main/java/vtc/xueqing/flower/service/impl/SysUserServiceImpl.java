package vtc.xueqing.flower.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.entity.SysUserWithRole;
import vtc.xueqing.flower.entity.SysUserRole;
import vtc.xueqing.flower.mapper.SysUserMapper;
import vtc.xueqing.flower.service.SysUserService;
import vtc.xueqing.flower.service.SysUserRoleService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户表;(sys_user)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    
    @Override
    public boolean saveOrUpdateWithRole(SysUserWithRole sysUserWithRole) {
        // 保存或更新用户信息
        // 注意：saveOrUpdate方法在新增用户时会自动将生成的ID设置回sysUserWithRole对象中
        boolean saved = this.saveOrUpdate(sysUserWithRole);
        
        if (saved && sysUserWithRole.getRoleId() != null) {
            // 先删除用户现有的角色关联关系
            sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, sysUserWithRole.getId()));
            
            // 再新增用户角色关联关系
            SysUserRole sysUserRole = SysUserRole.builder()
                    .userId(sysUserWithRole.getId())
                    .roleId(sysUserWithRole.getRoleId())
                    .createTime(LocalDateTime.now())
                    .build();
            sysUserRoleService.save(sysUserRole);
        }
        return saved;
    }
    
    @Override
    public Page<SysUserWithRole> pageUsersWithRole(Page<SysUser> page, String keyword, Integer status) {
        // 构造查询条件
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        
        // 关键词模糊查询
        if (keyword != null && !keyword.trim().isEmpty()) {
            // 根据关键词模糊查询用户名、昵称、邮箱
            queryWrapper.and(wrapper -> wrapper
                    .like(SysUser::getUsername, keyword)
                    .or()
                    .like(SysUser::getNickname, keyword)
                    .or()
                    .like(SysUser::getEmail, keyword));
        }
        
        // 状态精确查询
        queryWrapper.eq(ObjectUtil.isNotNull(status), SysUser::getStatus, status);

        // 先查询用户分页数据
        Page<SysUser> userPage = this.page(page, queryWrapper);
        
        // 构造返回的分页对象
        Page<SysUserWithRole> resultPage = new Page<>();
        resultPage.setCurrent(userPage.getCurrent());
        resultPage.setSize(userPage.getSize());
        resultPage.setTotal(userPage.getTotal());
        resultPage.setPages(userPage.getPages());
        
        // 处理空数据情况
        if (userPage.getRecords() == null || userPage.getRecords().isEmpty()) {
            resultPage.setRecords(Collections.emptyList());
            return resultPage;
        }
        
        // 查询用户对应的角色信息
        List<Long> userIds = userPage.getRecords().stream()
                .map(SysUser::getId)
                .filter(id -> id != null) // 过滤掉空ID
                .collect(Collectors.toList());
        
        List<SysUserRole> userRoles;
        // 只有当userIds不为空时才查询角色信息
        if (!userIds.isEmpty()) {
            // 查询用户角色关联信息
            userRoles = sysUserRoleService.list(
                    new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId, userIds)
            );
        } else {
            userRoles = Collections.emptyList();
        }

        // 构造包含角色ID的用户信息列表
        List<SysUserWithRole> userWithRoleList = userPage.getRecords().stream()
                .filter(user -> user != null && user.getId() != null) // 过滤掉空用户和无ID用户
                .map(user -> {
                    SysUserWithRole userWithRole = new SysUserWithRole();
                    // 拷贝用户属性
                    BeanUtils.copyProperties(user, userWithRole);
                    
                    // 设置角色ID
                    userRoles.stream()
                            .filter(userRole -> userRole.getUserId() != null && userRole.getUserId().equals(user.getId()))
                            .findFirst()
                            .ifPresent(userRole -> userWithRole.setRoleId(userRole.getRoleId()));
                    
                    return userWithRole;
                })
                .collect(Collectors.toList());
        
        resultPage.setRecords(userWithRoleList);
        return resultPage;
    }
}