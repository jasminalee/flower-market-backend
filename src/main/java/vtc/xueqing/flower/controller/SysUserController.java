package vtc.xueqing.flower.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.service.SysUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

 /**
 * 系统用户表;(sys_user)表控制层
 * @author : Xueqing
 * @date : 2025-9-25
 */
@Api(tags = "系统用户表对象功能接口")
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<SysUser> queryById(@PathVariable Long id){
        return ResponseEntity.ok(sysUserService.getById(id));
    }
    
    /** 
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param current 当前页码
     * @param size 每页大小
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseEntity<Page<SysUser>> paginQuery(SysUser sysUser, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        return ResponseEntity.ok(sysUserService.page(getPage(current, size), new LambdaQueryWrapper<>(sysUser)));
    }
    
    /** 
     * 新增更新系统与用户
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增/更新系统与用户")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody SysUser sysUser){
        return ResponseEntity.ok(sysUserService.saveOrUpdate(sysUser));
    }
    
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(sysUserService.removeById(id));
    }
}