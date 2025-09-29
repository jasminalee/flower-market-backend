package vtc.xueqing.flower.controller;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.SysRole;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.service.SysRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vtc.xueqing.flower.common.ResponseResult;

import java.util.List;

/**
 * 系统角色表;(sys_role)表控制层
 * @author : Xueqing
 */
// @Api(tags = "系统角色表对象功能接口")
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<SysRole> queryById(@PathVariable Long id){
        return success(sysRoleService.getById(id));
    }
    @ApiOperation("分页查询根据关键字模糊查询")
    @GetMapping("/page")
    public ResponseResult<Page<SysRole>> paginQuery(String keywords,
                                                    @RequestParam(defaultValue = "1") Long current,
                                                    @RequestParam(defaultValue = "10") Long size){
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(keywords), SysRole::getRoleName, keywords)
                .or()
                .like(StrUtil.isNotBlank(keywords), SysRole::getRoleCode, keywords)
                .or()
                .like(StrUtil.isNotBlank(keywords), SysRole::getDescription, keywords);
        return success(sysRoleService.page(getPage(current, size), wrapper));
    }
    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<SysRole>> list(SysRole sysRole){
        return success(sysRoleService.list(new LambdaQueryWrapper<>(sysRole)));
    }
    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody SysRole sysRole){
        return success(sysRoleService.saveOrUpdate(sysRole));
    }
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id){
        return success(sysRoleService.removeById(id));
    }
}