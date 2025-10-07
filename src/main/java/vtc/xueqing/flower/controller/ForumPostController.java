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
import vtc.xueqing.flower.entity.ForumPost;
import vtc.xueqing.flower.service.ForumPostService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * 论坛帖子表;(forum_post)表控制层
 * @author : Xueqing
 */
@Api(tags = "论坛帖子表对象功能接口")
@RestController
@RequestMapping("/forumPost")
public class ForumPostController extends BaseController {
    @Autowired
    private ForumPostService forumPostService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<ForumPost> queryById(
            @ApiParam("帖子ID") 
            @NotNull(message = "帖子ID不能为空") 
            @Min(value = 1, message = "帖子ID必须大于0") 
            @PathVariable Long id){
        return success(forumPostService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<ForumPost>> paginQuery(ForumPost forumPost, 
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        LambdaQueryWrapper<ForumPost> wrapper = new LambdaQueryWrapper<>(forumPost);
        Page<ForumPost> pageResult = forumPostService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (pageResult.getRecords() == null || pageResult.getRecords().isEmpty()) {
            pageResult.setRecords(Collections.emptyList());
        }
        
        return success(pageResult);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<ForumPost>> list(ForumPost forumPost){
        LambdaQueryWrapper<ForumPost> wrapper = new LambdaQueryWrapper<>(forumPost);
        return success(forumPostService.list(wrapper));
    }
    
    @ApiOperation("根据板块ID分页查询帖子列表")
    @GetMapping("/pageByCategory/{categoryId}")
    public ResponseResult<Page<ForumPost>> pageByCategoryId(
            @ApiParam("板块ID")
            @NotNull(message = "板块ID不能为空")
            @Min(value = 1, message = "板块ID必须大于0")
            @PathVariable Long categoryId,
            @ApiParam("页码")
            @RequestParam(defaultValue = "1")
            @Min(value = 1, message = "页码必须大于0") Long current,
            @ApiParam("每页数量")
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "每页数量必须大于0") Long size) {
        LambdaQueryWrapper<ForumPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ForumPost::getCategoryId, categoryId)
               .eq(ForumPost::getStatus, 1)
               .orderByDesc(ForumPost::getIsTop, ForumPost::getCreateTime);
        Page<ForumPost> pageResult = forumPostService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (pageResult.getRecords() == null || pageResult.getRecords().isEmpty()) {
            pageResult.setRecords(Collections.emptyList());
        }
        
        return success(pageResult);
    }
    
    @ApiOperation("获取热门帖子列表（按浏览量排序）")
    @GetMapping("/hot")
    public ResponseResult<List<ForumPost>> getHotPosts(
            @ApiParam("数量限制")
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "数量必须大于0") Integer limit) {
        LambdaQueryWrapper<ForumPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ForumPost::getStatus, 1)
               .orderByDesc(ForumPost::getViewCount)
               .last("LIMIT " + limit);
        return success(forumPostService.list(wrapper));
    }
    
    @ApiOperation("获取精华帖子列表")
    @GetMapping("/essence")
    public ResponseResult<List<ForumPost>> getEssencePosts(
            @ApiParam("数量限制")
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "数量必须大于0") Integer limit) {
        LambdaQueryWrapper<ForumPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ForumPost::getStatus, 1)
               .eq(ForumPost::getIsEssence, 1)
               .orderByDesc(ForumPost::getCreateTime)
               .last("LIMIT " + limit);
        return success(forumPostService.list(wrapper));
    }
    
    @ApiOperation("根据用户ID查询帖子列表")
    @GetMapping("/byUser/{userId}")
    public ResponseResult<Page<ForumPost>> getByUserId(
            @ApiParam("用户ID")
            @NotNull(message = "用户ID不能为空")
            @Min(value = 1, message = "用户ID必须大于0")
            @PathVariable Long userId,
            @ApiParam("页码")
            @RequestParam(defaultValue = "1")
            @Min(value = 1, message = "页码必须大于0") Long current,
            @ApiParam("每页数量")
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "每页数量必须大于0") Long size) {
        LambdaQueryWrapper<ForumPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ForumPost::getUserId, userId)
               .orderByDesc(ForumPost::getCreateTime);
        Page<ForumPost> pageResult = forumPostService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (pageResult.getRecords() == null || pageResult.getRecords().isEmpty()) {
            pageResult.setRecords(Collections.emptyList());
        }
        
        return success(pageResult);
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Valid ForumPost forumPost){
        return success(forumPostService.saveOrUpdate(forumPost));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(
            @ApiParam("帖子ID") 
            @NotNull(message = "帖子ID不能为空") 
            @Min(value = 1, message = "帖子ID必须大于0") 
            @PathVariable Long id){
        return success(forumPostService.removeById(id));
    }
}