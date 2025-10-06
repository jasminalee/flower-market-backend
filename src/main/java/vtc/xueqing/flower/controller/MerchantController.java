package vtc.xueqing.flower.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.MerchantProduct;
import vtc.xueqing.flower.entity.Product;
import vtc.xueqing.flower.entity.ProductSku;
import vtc.xueqing.flower.service.MerchantProductService;
import vtc.xueqing.flower.service.ProductService;
import vtc.xueqing.flower.service.ProductSkuService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 商户操作控制器
 * 专门处理商户上架产品、管理库存等操作
 * @author : Xueqing
 */
// @Api(tags = "商户操作接口")
@RestController
@RequestMapping("/merchant")
public class MerchantController extends BaseController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductSkuService productSkuService;
    
    @Autowired
    private MerchantProductService merchantProductService;

    @ApiOperation("查询所有可上架的产品")
    @GetMapping("/products")
    public ResponseResult<Page<Product>> listAvailableProducts(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        // 查询所有状态为上架的产品
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getStatus, 1);
        return success(productService.page(getPage(current, size), wrapper));
    }

    @ApiOperation("查询产品的SKU列表")
    @GetMapping("/product/{productId}/skus")
    public ResponseResult<List<ProductSku>> listProductSkus(@PathVariable Long productId) {
        LambdaQueryWrapper<ProductSku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductSku::getProductId, productId)
               .eq(ProductSku::getStatus, 1);
        return success(productSkuService.list(wrapper));
    }

    @ApiOperation("商户上架产品")
    @PostMapping("/product/onShelf")
    public ResponseResult<String> onShelfProduct(@RequestBody MerchantProduct merchantProduct) {
        // 这里可以添加商户身份验证逻辑
        // merchantProduct应该包含: merchantId, productId, skuId, price, stock
        
        // 检查SKU信息
        ProductSku productSku = productSkuService.getById(merchantProduct.getSkuId());
        if (productSku == null) {
            return fail("产品sku不可为空");
        }
        
        // 检查商户上架的库存不能超过SKU的参考库存
        if (merchantProduct.getStock() > productSku.getStock()) {
            return fail("检查商户上架的库存不能超过SKU的参考库存");
        }
        
        // 检查是否已经上架过该产品
        LambdaQueryWrapper<MerchantProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MerchantProduct::getMerchantId, merchantProduct.getMerchantId())
               .eq(MerchantProduct::getProductId, merchantProduct.getProductId())
               .eq(MerchantProduct::getSkuId, merchantProduct.getSkuId());
        
        MerchantProduct existing = merchantProductService.getOne(wrapper);
        if (existing != null) {
            // 如果已存在，更新价格和库存
            // 检查更新的库存不能超过SKU的参考库存
            if (merchantProduct.getStock() > productSku.getStock()) {
                return fail("检查更新的库存不能超过SKU的参考库存");
            }
            
            existing.setPrice(merchantProduct.getPrice())
                    .setStock(merchantProduct.getStock())
                    .setStatus(1); // 设置为上架状态
            merchantProductService.updateById(existing);
            return success();
        } else {
            // 如果不存在，新增上架记录
            merchantProduct.setStatus(1); // 设置为上架状态
            return success();
        }
    }

    @ApiOperation("商户下架产品")
    @PostMapping("/product/offShelf/{merchantProductId}")
    public ResponseResult<Boolean> offShelfProduct(@PathVariable Long merchantProductId) {
        MerchantProduct merchantProduct = merchantProductService.getById(merchantProductId);
        if (merchantProduct != null) {
            merchantProduct.setStatus(0); // 设置为下架状态
            return success(merchantProductService.updateById(merchantProduct));
        }
        return success(false);
    }

    @ApiOperation("更新产品库存")
    @PostMapping("/product/updateStock")
    public ResponseResult<String> updateStock(@RequestBody MerchantProduct merchantProduct) {
        // 检查SKU信息
        ProductSku productSku = productSkuService.getById(merchantProduct.getSkuId());
        if (productSku == null) {
            return fail("产品sku不可为空");
        }
        
        // 检查商户更新的库存不能超过SKU的参考库存
        if (merchantProduct.getStock() > productSku.getStock()) {
            return fail("检查商户更新的库存不能超过SKU的参考库存");
        }
        
        // 只更新库存信息
        LambdaQueryWrapper<MerchantProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MerchantProduct::getMerchantId, merchantProduct.getMerchantId())
               .eq(MerchantProduct::getProductId, merchantProduct.getProductId())
               .eq(MerchantProduct::getSkuId, merchantProduct.getSkuId());
        
        MerchantProduct existing = merchantProductService.getOne(wrapper);
        if (existing != null) {
            existing.setStock(merchantProduct.getStock());
            return success();
        }
        return success();
    }

    @ApiOperation("商户产品列表")
    @GetMapping("/myProducts")
    public ResponseResult<Page<MerchantProduct>> listMyProducts(
            @RequestParam Long merchantId,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        LambdaQueryWrapper<MerchantProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MerchantProduct::getMerchantId, merchantId);
        Page<MerchantProduct> pageResult = merchantProductService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (pageResult.getRecords() == null || pageResult.getRecords().isEmpty()) {
            pageResult.setRecords(Collections.emptyList());
        }
        
        return success(pageResult);
    }
}