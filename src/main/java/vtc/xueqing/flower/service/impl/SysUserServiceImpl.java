package vtc.xueqing.flower.service.impl;

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
import java.util.List;
import java.util.Map;
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
            // 创建或更新用户角色关联
            SysUserRole sysUserRole = SysUserRole.builder()
                    .userId(sysUserWithRole.getId())
                    .roleId(sysUserWithRole.getRoleId())
                    .build();
            // 保存或更新用户角色关联
            sysUserRoleService.saveOrUpdate(sysUserRole, new LambdaQueryWrapper<>(sysUserRole));
        }
        return saved;
    }
    
    @Override
    public Page<SysUserWithRole> pageUsersWithRole(Page<SysUser> page, SysUser sysUser) {
        // 先查询用户分页数据
        Page<SysUser> userPage = this.page(page, new LambdaQueryWrapper<>(sysUser));
        


        // 查询用户对应的角色信息
        List<Long> userIds = userPage.getRecords().stream()
                .map(SysUser::getId)
                .collect(Collectors.toList());
        
        // 查询用户角色关联信息
        List<SysUserRole> userRoles = sysUserRoleService.list(
                new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId, userIds)
        );
        Map<Long, Long> userRoleMap = userRoles.stream().collect(Collectors.toMap(SysUserRole::getUserId, SysUserRole::getRoleId));

        List<SysUserWithRole> collect = userPage.getRecords().stream().map(record -> {
            SysUserWithRole userWithRole = new SysUserWithRole();
            BeanUtils.copyProperties(record, userWithRole);
            userWithRole.setRoleId(userRoleMap.get(record.getId()));
            return userWithRole;
        }).collect(Collectors.toList());

        Page<SysUserWithRole> resultPage = new Page<>(page.getCurrent(), page.getSize());
        resultPage.setTotal(userPage.getTotal());
        resultPage.setRecords(collect);
        return resultPage;
    }
}