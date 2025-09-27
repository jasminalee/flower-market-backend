
package vtc.xueqing.flower.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.entity.SysRolePermission;
import vtc.xueqing.flower.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * 角色权限关联表;(sys_role_permission)表控制层
 * @author : Xueqing
 */
@Api(tags = "角色权限关联表对象功能接口")
@RestController
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController extends BaseController {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<SysRolePermission> queryById(@PathVariable Long id){
        return ResponseEntity.ok(sysRolePermissionService.getById(id));
    }
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseEntity<Page<SysRolePermission>> paginQuery(SysRolePermission sysRolePermission, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        return ResponseEntity.ok(sysRolePermissionService.page(getPage(current, size), new LambdaQueryWrapper<>(sysRolePermission)));
    }
    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody SysRolePermission sysRolePermission){
        return ResponseEntity.ok(sysRolePermissionService.saveOrUpdate(sysRolePermission));
    }
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(sysRolePermissionService.removeById(id));
    }
}