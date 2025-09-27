package vtc.xueqing.flower.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.entity.SysUserWithRole;
import vtc.xueqing.flower.service.SysUserService;
import vtc.xueqing.flower.common.ResponseResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限控制层表-控制层
 *
 * @author : Xueqing
 */
@Api(tags = "权限认证接口")
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户登录")
    @PostMapping("/login") // @RequestBody 接收一个json
    public ResponseResult login(@RequestBody SysUser loginRequest) {
        // 根据用户名查找用户
        SysUser sysUser = sysUserService.getOne(
            new LambdaQueryWrapper<>(loginRequest)
        );

        // 检查用户是否存在
        if (sysUser == null)
            return fail("用户名或者密码错误！");

        // 检查用户状态
        if (sysUser.getStatus() != null && sysUser.getStatus() == 0)
            return fail("用户已被禁用");
        return success("登录成功");
    }
}