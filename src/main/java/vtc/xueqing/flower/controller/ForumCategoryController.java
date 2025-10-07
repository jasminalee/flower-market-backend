package vtc.xueqing.flower.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.ForumCategory;
import vtc.xueqing.flower.service.ForumCategoryService;
import vtc.xueqing.flower.vo.ForumCategoryTreeVO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 论坛板块表;(forum_category)表控制层
 * @author : Xueqing
 */
@Api(tags = "论坛板块表对象功能接口")
@RestController
@RequestMapping("/forumCategory")
public class ForumCategoryController extends BaseController {
    @Autowired
    private ForumCategoryService forumCategoryService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<ForumCategory> queryById(
            @ApiParam("板块ID") 
            @NotNull(message = "板块ID不能为空") 
            @Min(value = 1, message = "板块ID必须大于0") 
            @PathVariable Long id){
        return success(forumCategoryService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<ForumCategory>> paginQuery(ForumCategory forumCategory, 
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        LambdaQueryWrapper<ForumCategory> wrapper = new LambdaQueryWrapper<>(forumCategory);
        Page<ForumCategory> pageResult = forumCategoryService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (pageResult.getRecords() == null || pageResult.getRecords().isEmpty()) {
            pageResult.setRecords(Collections.emptyList());
        }
        
        return success(pageResult);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<ForumCategory>> list(ForumCategory forumCategory){
        LambdaQueryWrapper<ForumCategory> wrapper = new LambdaQueryWrapper<>(forumCategory);
        return success(forumCategoryService.list(wrapper));
    }
    
    @ApiOperation("树状结构列表查询")
    @GetMapping("/tree")
    public ResponseResult<List<ForumCategoryTreeVO>> tree(ForumCategory forumCategory) {
        // 获取所有分类
        List<ForumCategory> allCategories = forumCategoryService.list(new LambdaQueryWrapper<>(forumCategory));
        
        // 转换为树状结构
        List<ForumCategoryTreeVO> tree = buildCategoryTree(allCategories, 0L);
        
        return success(tree);
    }
    
    /**
     * 构建分类树
     * @param categories 所有分类
     * @param parentId 父节点ID
     * @return 树状结构
     */
    private List<ForumCategoryTreeVO> buildCategoryTree(List<ForumCategory> categories, Long parentId) {
        return categories.stream()
                .filter(category -> category.getParentId().equals(parentId))
                .map(category -> {
                    ForumCategoryTreeVO vo = new ForumCategoryTreeVO(category);
                    vo.setChildren(buildCategoryTree(categories, category.getId()));
                    return vo;
                })
                .collect(Collectors.toList());
    }
    
    @ApiOperation("获取启用的板块列表（用于前端展示）")
    @GetMapping("/available")
    public ResponseResult<List<ForumCategory>> getAvailableCategories() {
        LambdaQueryWrapper<ForumCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ForumCategory::getStatus, 1)
               .orderByAsc(ForumCategory::getSort);
        return success(forumCategoryService.list(wrapper));
    }
    
    @ApiOperation("根据父板块ID获取子板块列表")
    @GetMapping("/children/{parentId}")
    public ResponseResult<List<ForumCategory>> getChildrenByParentId(
            @ApiParam("父板块ID")
            @NotNull(message = "父板块ID不能为空")
            @Min(value = 0, message = "父板块ID不能小于0")
            @PathVariable Long parentId) {
        LambdaQueryWrapper<ForumCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ForumCategory::getParentId, parentId)
               .eq(ForumCategory::getStatus, 1)
               .orderByAsc(ForumCategory::getSort);
        return success(forumCategoryService.list(wrapper));
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Valid ForumCategory forumCategory){
        return success(forumCategoryService.saveOrUpdate(forumCategory));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(
            @ApiParam("板块ID") 
            @NotNull(message = "板块ID不能为空") 
            @Min(value = 1, message = "板块ID必须大于0") 
            @PathVariable Long id){
        return success(forumCategoryService.removeById(id));
    }
}