
package vtc.xueqing.flower.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.entity.SysRole;
import vtc.xueqing.flower.service.SysRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * 系统角色表;(sys_role)表控制层
 * @author : Xueqing
 */
@Api(tags = "系统角色表对象功能接口")
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<SysRole> queryById(@PathVariable Long id){
        return ResponseEntity.ok(sysRoleService.getById(id));
    }
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseEntity<Page<SysRole>> paginQuery(SysRole sysRole, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        return ResponseEntity.ok(sysRoleService.page(getPage(current, size), new LambdaQueryWrapper<>(sysRole)));
    }
    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody SysRole sysRole){
        return ResponseEntity.ok(sysRoleService.saveOrUpdate(sysRole));
    }
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(sysRoleService.removeById(id));
    }
}