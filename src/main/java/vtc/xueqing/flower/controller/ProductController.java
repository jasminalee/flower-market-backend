package vtc.xueqing.flower.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.Product;
import vtc.xueqing.flower.entity.ProductSku;
import vtc.xueqing.flower.service.ProductService;
import vtc.xueqing.flower.service.ProductSkuService;
import vtc.xueqing.flower.vo.ProductDetailVO;
import vtc.xueqing.flower.vo.ProductListVO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * 产品信息表;(product)表控制层
 * @author : Xueqing
 */
@Api(tags = "产品信息表对象功能接口")
@Validated
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductSkuService productSkuService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<Product> queryById(
            @ApiParam("产品ID") 
            @NotNull(message = "产品ID不能为空") 
            @Min(value = 1, message = "产品ID必须大于0") 
            @PathVariable Long id){
        return success(productService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<Product>> paginQuery(Product product, 
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>(product);
        Page<Product> productPage = productService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (productPage.getRecords() == null || productPage.getRecords().isEmpty()) {
            productPage.setRecords(Collections.emptyList());
        }
        
        return success(productPage);
    }
    
    @ApiOperation("主页商品列表（支持分类和搜索）")
    @GetMapping("/homepage")
    public ResponseResult<Page<ProductListVO>> homepageProducts(
            @ApiParam("分类ID") @RequestParam(required = false) Long categoryId,
            @ApiParam("搜索关键字") @RequestParam(required = false) String keyword,
            @ApiParam("页码") @RequestParam(defaultValue = "1") @Min(value = 1, message = "页码必须大于0") Long current,
            @ApiParam("每页数量") @RequestParam(defaultValue = "10") @Min(value = 1, message = "每页数量必须大于0") Long size) {
        
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        
        // 只显示上架的商品
        wrapper.eq(Product::getStatus, 1);
        
        // 按分类筛选
        if (categoryId != null) {
            wrapper.eq(Product::getCategoryId, categoryId);
        }
        
        // 按关键字搜索
        if (keyword != null && !keyword.isEmpty()) {
            wrapper.and(w -> w.like(Product::getProductName, keyword))
                    // .or()
                    // .like(Product::getBrand, keyword)
                    // .or()
                    // .like(Product::getDescription, keyword))
                    ;
        }
        
        // 按创建时间倒序排列
        wrapper.orderByDesc(Product::getCreateTime);
        
        Page<Product> productPage = productService.page(getPage(current, size), wrapper);
        Page<ProductListVO> voPage = new Page<>(current, size, productPage.getTotal());
        List<ProductListVO> voList = productService.convertToProductListVO(productPage.getRecords());
        voPage.setRecords(voList);
        
        return success(voPage);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<Product>> list(Product product){
        return success(productService.list(new LambdaQueryWrapper<>(product)));
    }
    
    @ApiOperation("获取产品详情包括SKU信息")
    @GetMapping("/detail/{id}")
    public ResponseResult<ProductDetailVO> getProductDetail(
            @ApiParam("产品ID") 
            @NotNull(message = "产品ID不能为空") 
            @Min(value = 1, message = "产品ID必须大于0") 
            @PathVariable Long id) {
        Product product = productService.getById(id);
        if (product == null) {
            return fail(new ProductDetailVO());
        }
        
        LambdaQueryWrapper<ProductSku> skuWrapper = new LambdaQueryWrapper<>();
        skuWrapper.eq(ProductSku::getProductId, id)
                  .eq(ProductSku::getStatus, 1);
        List<ProductSku> skus = productSkuService.list(skuWrapper);
        
        ProductDetailVO productDetailVO = new ProductDetailVO();
        productDetailVO.setProduct(product);
        productDetailVO.setSkus(skus);
        
        return success(productDetailVO);
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@Valid @RequestBody Product product){
        return success(productService.saveOrUpdate(product));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(
            @ApiParam("产品ID") 
            @NotNull(message = "产品ID不能为空") 
            @Min(value = 1, message = "产品ID必须大于0") 
            @PathVariable Long id){
        return success(productService.removeById(id));
    }
}