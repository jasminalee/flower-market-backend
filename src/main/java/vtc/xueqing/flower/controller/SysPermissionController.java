package vtc.xueqing.flower.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.entity.SysPermission;
import vtc.xueqing.flower.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vtc.xueqing.flower.common.ResponseResult;
/**
 * 系统权限表;(sys_permission)表控制层
 * @author : Xueqing
 */
@Api(tags = "系统权限表对象功能接口")
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController extends BaseController {
    @Autowired
    private SysPermissionService sysPermissionService;
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<SysPermission> queryById(@PathVariable Long id){
        return success(sysPermissionService.getById(id));
    }
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<SysPermission>> paginQuery(SysPermission sysPermission, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        return success(sysPermissionService.page(getPage(current, size), new LambdaQueryWrapper<>(sysPermission)));
    }
    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody SysPermission sysPermission){
        return success(sysPermissionService.saveOrUpdate(sysPermission));
    }
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id){
        return success(sysPermissionService.removeById(id));
    }
}