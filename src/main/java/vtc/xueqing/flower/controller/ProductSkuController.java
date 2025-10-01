package vtc.xueqing.flower.controller;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.ProductSku;
import vtc.xueqing.flower.service.ProductSkuService;

import java.util.List;

/**
 * 产品SKU表;(product_sku)表控制层
 * @author : Xueqing
 */
// @Api(tags = "产品SKU表对象功能接口")
@RestController
@RequestMapping("/productSku")
public class ProductSkuController extends BaseController {
    @Autowired
    private ProductSkuService productSkuService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<ProductSku> queryById(@PathVariable Long id){
        return success(productSkuService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<ProductSku>> paginQuery(ProductSku productSku, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        LambdaQueryWrapper<ProductSku> wrapper = new LambdaQueryWrapper<>(productSku);
        return success(productSkuService.page(getPage(current, size), wrapper));
    }

    @ApiOperation("根据产品ID查询SKU列表")
    @GetMapping("/product/{productId}")
    public ResponseResult<List<ProductSku>> listByProductId(@PathVariable Long productId) {
        LambdaQueryWrapper<ProductSku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductSku::getProductId, productId)
               .eq(ProductSku::getStatus, 1);
        return success(productSkuService.list(wrapper));
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<ProductSku>> list(ProductSku productSku){
        LambdaQueryWrapper<ProductSku> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(productSku.getSkuName()),ProductSku::getSkuName, productSku.getSkuName())
                .like(StrUtil.isNotBlank(productSku.getSkuCode()),ProductSku::getSkuCode, productSku.getSkuCode())
        ;

        return success(productSkuService.list(wrapper));
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody ProductSku productSku){
        return success(productSkuService.saveOrUpdate(productSku));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id){
        return success(productSkuService.removeById(id));
    }
}