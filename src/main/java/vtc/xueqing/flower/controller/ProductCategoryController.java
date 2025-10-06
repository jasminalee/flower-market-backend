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
import vtc.xueqing.flower.entity.ProductCategory;
import vtc.xueqing.flower.service.ProductCategoryService;
import vtc.xueqing.flower.vo.ProductCategoryTreeVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品分类表;(product_category)表控制层
 * @author : Xueqing
 */
// @Api(tags = "产品分类表对象功能接口")
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController extends BaseController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<ProductCategory> queryById(
            @ApiParam("分类ID") 
            @NotNull(message = "分类ID不能为空") 
            @Min(value = 1, message = "分类ID必须大于0") 
            @PathVariable Long id){
        return success(productCategoryService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<ProductCategory>> paginQuery(ProductCategory productCategory, 
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        LambdaQueryWrapper<ProductCategory> wrapper = new LambdaQueryWrapper<>(productCategory);
        Page<ProductCategory> pageResult = productCategoryService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (pageResult.getRecords() == null || pageResult.getRecords().isEmpty()) {
            pageResult.setRecords(Collections.emptyList());
        }
        
        return success(pageResult);
    }
    
    @ApiOperation("获取主页用的分类列表（只包含启用的一级分类）")
    @GetMapping("/homepage")
    public ResponseResult<List<ProductCategory>> getHomepageCategories() {
        LambdaQueryWrapper<ProductCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductCategory::getStatus, 1)  // 只查询启用的分类
               .eq(ProductCategory::getParentId, 0)  // 只查询一级分类
               .orderByAsc(ProductCategory::getSort);  // 按排序号升序排列
        
        return success(productCategoryService.list(wrapper));
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<ProductCategory>> list(ProductCategory productCategory){
        return success(productCategoryService.list(new LambdaQueryWrapper<>(productCategory)));
    }
    
    @ApiOperation("获取分类树结构")
    @GetMapping("/tree")
    public ResponseResult<List<ProductCategoryTreeVO>> getCategoryTree() {
        // 获取所有分类
        List<ProductCategory> allCategories = productCategoryService.list();
        
        // 构造分类树
        List<ProductCategoryTreeVO> rootCategories = allCategories.stream()
                .filter(category -> category.getParentId() == null || category.getParentId() == 0)
                .map(ProductCategoryTreeVO::new)
                .collect(Collectors.toList());
        
        // 递归设置子分类
        rootCategories.forEach(this::setChildren);
        
        return success(rootCategories);
    }
    
    private void setChildren(ProductCategoryTreeVO parent) {
        List<ProductCategory> allCategories = productCategoryService.list();
        List<ProductCategoryTreeVO> children = allCategories.stream()
                .filter(category -> category.getParentId() != null && category.getParentId().equals(parent.getId()))
                .map(ProductCategoryTreeVO::new)
                .collect(Collectors.toList());
        
        if (!children.isEmpty()) {
            parent.setChildren(children);
            children.forEach(this::setChildren);
        }
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody ProductCategory productCategory){
        return success(productCategoryService.saveOrUpdate(productCategory));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(
            @ApiParam("分类ID") 
            @NotNull(message = "分类ID不能为空") 
            @Min(value = 1, message = "分类ID必须大于0") 
            @PathVariable Long id){
        return success(productCategoryService.removeById(id));
    }
}