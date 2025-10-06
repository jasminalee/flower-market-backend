package vtc.xueqing.flower.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.Comment;
import vtc.xueqing.flower.service.CommentService;

import java.util.List;
import java.util.Objects;

/**
 * 通用评论表;(comment)表控制层
 *
 * @author : Xueqing
 */
@Api(tags = "通用评论表对象功能接口")
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<Comment> queryById(@PathVariable Long id) {
        return success(commentService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<Comment>> paginQuery(Comment comment,
                                                    @RequestParam(defaultValue = "1") Long current,
                                                    @RequestParam(defaultValue = "10") Long size) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>(comment);
        return success(commentService.page(getPage(current, size), wrapper));
    }

    @ApiOperation("根据来源ID和类型查询评论列表")
    @GetMapping("/source")
    public ResponseResult<List<Comment>> listBySource(
            @RequestParam Long sourceId,
            @RequestParam String sourceType,
            Long parentId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getSourceId, sourceId)
                .eq(Comment::getSourceType, sourceType)
                .eq(Objects.nonNull(parentId), Comment::getParentId, parentId)
                .eq(Comment::getStatus, 1)
                .orderByDesc(Comment::getCreateTime);
        return success(commentService.list(wrapper));
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody Comment comment) {
        return success(commentService.saveOrUpdate(comment));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id) {
        return success(commentService.removeById(id));
    }
}