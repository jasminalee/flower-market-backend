package vtc.xueqing.flower.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.SysUserRole;
import vtc.xueqing.flower.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vtc.xueqing.flower.common.ResponseResult;
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
    public ResponseResult<SysUserRole> queryById(@PathVariable Long id){
        return success(sysUserRoleService.getById(id));
    }
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<SysUserRole>> paginQuery(SysUserRole sysUserRole, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        return success(sysUserRoleService.page(getPage(current, size), new LambdaQueryWrapper<>(sysUserRole)));
    }
    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody SysUserRole sysUserRole){
        return success(sysUserRoleService.saveOrUpdate(sysUserRole));
    }
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id){
        return success(sysUserRoleService.removeById(id));
    }
}