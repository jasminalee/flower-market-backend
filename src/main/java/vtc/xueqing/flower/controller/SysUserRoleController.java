
package vtc.xueqing.flower.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.entity.SysUserRole;
import vtc.xueqing.flower.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * 用户角色关联表;(sys_user_role)表控制层
 * @author : Xueqing
 */
@Api(tags = "用户角色关联表对象功能接口")
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController extends BaseController {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<SysUserRole> queryById(@PathVariable Long id){
        return ResponseEntity.ok(sysUserRoleService.getById(id));
    }
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseEntity<Page<SysUserRole>> paginQuery(SysUserRole sysUserRole, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        return ResponseEntity.ok(sysUserRoleService.page(getPage(current, size), new LambdaQueryWrapper<>(sysUserRole)));
    }
    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody SysUserRole sysUserRole){
        return ResponseEntity.ok(sysUserRoleService.saveOrUpdate(sysUserRole));
    }
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(sysUserRoleService.removeById(id));
    }
}