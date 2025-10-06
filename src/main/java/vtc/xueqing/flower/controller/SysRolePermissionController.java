
package vtc.xueqing.flower.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import vtc.xueqing.flower.common.ResponseResult;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.entity.SysRolePermission;
import vtc.xueqing.flower.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vtc.xueqing.flower.config.BaseController;

import java.util.Collections;
import java.util.List;

/**
 * 角色权限关联表;(sys_role_permission)表控制层
 * @author : Xueqing
 */
// @Api(tags = "角色权限关联表对象功能接口")
@RestController
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController extends BaseController {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<SysRolePermission> queryById(@PathVariable Long id){
        return success(sysRolePermissionService.getById(id));
    }
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<SysRolePermission>> paginQuery(SysRolePermission sysRolePermission, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        LambdaQueryWrapper<SysRolePermission> wrapper = new LambdaQueryWrapper<>();
        Page<SysRolePermission> pageResult = sysRolePermissionService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (pageResult.getRecords() == null || pageResult.getRecords().isEmpty()) {
            pageResult.setRecords(Collections.emptyList());
        }
        
        return success(pageResult);
    }
    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<SysRolePermission>> list(SysRolePermission sysRolePermission){
        return success(sysRolePermissionService.list(new LambdaQueryWrapper<>(sysRolePermission)));
    }
    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody SysRolePermission sysRolePermission){
        return success(sysRolePermissionService.saveOrUpdate(sysRolePermission));
    }
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id){
        return success(sysRolePermissionService.removeById(id));
    }
}