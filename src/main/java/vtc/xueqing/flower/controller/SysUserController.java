package vtc.xueqing.flower.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.entity.SysUserWithRole;
import vtc.xueqing.flower.service.SysUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vtc.xueqing.flower.common.ResponseResult;

/**
 * 系统用户表;(sys_user)表控制层
 *
 * @author : Xueqing
 */
// @Api(tags = "系统用户表对象功能接口")
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @ApiOperation("通过ID查询用户")
    @GetMapping("{id}")
    public ResponseResult<SysUser> queryById(@PathVariable Long id) {
        return success(sysUserService.getById(id));
    }


    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<SysUserWithRole>> paginQuery(@RequestParam(value = "keyword", required = false) String keyword,
                                                    @RequestParam(value = "status", required = false) Integer status,
                                                    @RequestParam(value = "current", defaultValue = "1") Long current,
                                                    @RequestParam(value = "size", defaultValue = "10") Long size) {
        Page<SysUser> page = getPage(current, size);
        Page<SysUserWithRole> result = sysUserService.pageUsersWithRole(page, keyword, status);
        return success(result);
    }

    @ApiOperation("新增/更新系统与用户(包含角色信息)")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody SysUserWithRole sysUserWithRole) {
        return success(sysUserService.saveOrUpdateWithRole(sysUserWithRole));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id) {
        return success(sysUserService.removeById(id));
    }
}