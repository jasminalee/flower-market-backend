package vtc.xueqing.flower.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;
import vtc.xueqing.flower.entity.MerchantProduct;
import vtc.xueqing.flower.entity.Product;
import vtc.xueqing.flower.entity.ProductSku;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.service.MerchantProductService;
import vtc.xueqing.flower.service.ProductService;
import vtc.xueqing.flower.service.ProductSkuService;
import vtc.xueqing.flower.service.SysUserService;
import vtc.xueqing.flower.vo.MerchantProductVO;
import vtc.xueqing.flower.vo.MerchantProductWithProductInfoVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商户产品关联表;(merchant_product)表控制层
 *
 * @author : Xueqing
 */
// @Api(tags = "商户产品关联表对象功能接口")
@RestController
@RequestMapping("/merchantProduct")
public class MerchantProductController extends BaseController {
    @Autowired
    private MerchantProductService merchantProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSkuService productSkuService;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<MerchantProduct> queryById(
            @ApiParam("商户产品ID")
            @NotNull(message = "商户产品ID不能为空")
            @Min(value = 1, message = "商户产品ID必须大于0")
            @PathVariable Long id) {
        return success(merchantProductService.getById(id));
    }

    @ApiOperation("分页查询（支持根据产品名称或编码查询）")
    @GetMapping("/page")
    public ResponseResult<Page<MerchantProductWithProductInfoVO>> paginQuery(
            @ApiParam("商户id(用户id)") @RequestParam(required = false) String merchantId,
            @ApiParam("产品名称") @RequestParam(required = false) String productName,
            @ApiParam("产品编码") @RequestParam(required = false) String productCode,
            @ApiParam("页码")
            @RequestParam(defaultValue = "1")
            @Min(value = 1, message = "页码必须大于0") Long current,
            @ApiParam("每页数量")
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "每页数量必须大于0") Long size) {

        // 构建查询条件
        LambdaQueryWrapper<MerchantProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(merchantId), MerchantProduct::getMerchantId, merchantId);
        // 如果提供了产品名称或编码，则先查询产品表获取匹配的产品ID
        if (productName != null || productCode != null) {
            LambdaQueryWrapper<Product> productWrapper = new LambdaQueryWrapper<>();
            productWrapper.like(StrUtil.isNotBlank(productName), Product::getProductName, productName);
            productWrapper.like(StrUtil.isNotBlank(productCode), Product::getProductCode, productCode);

            List<Product> products = productService.list(productWrapper);
            if (products.isEmpty()) {
                // 如果没有匹配的产品，返回空分页结果
                Page<MerchantProductWithProductInfoVO> emptyPage = new Page<>(current, size);
                emptyPage.setRecords(Collections.emptyList());
                emptyPage.setTotal(0L);
                return success(emptyPage);
            } else {
                // 获取匹配的产品ID列表
                List<Long> productIds = products.stream().map(Product::getId).collect(Collectors.toList());
                wrapper.in(MerchantProduct::getProductId, productIds);
            }
        }

        // 执行分页查询
        Page<MerchantProduct> merchantProductPage = merchantProductService.page(getPage(current, size), wrapper);

        // 转换为VO对象
        Page<MerchantProductWithProductInfoVO> voPage = new Page<>(current, size);
        voPage.setTotal(merchantProductPage.getTotal());

        List<MerchantProductWithProductInfoVO> voList = merchantProductPage.getRecords().stream().map(merchantProduct -> {
            MerchantProductWithProductInfoVO vo = new MerchantProductWithProductInfoVO();
            vo.setId(merchantProduct.getId());
            vo.setMerchantId(merchantProduct.getMerchantId());
            vo.setProductId(merchantProduct.getProductId());
            vo.setSkuId(merchantProduct.getSkuId());
            vo.setPrice(merchantProduct.getPrice());
            vo.setStock(merchantProduct.getStock());
            vo.setStatus(merchantProduct.getStatus());
            vo.setCreateTime(merchantProduct.getCreateTime());
            vo.setUpdateTime(merchantProduct.getUpdateTime());
            // 获取商户信息
            // SysUser merchant = sysUserService.getById(merchantProduct.getMerchantId());
            // vo.setMerchant(merchant);

            // 获取产品信息
            Product product = productService.getById(merchantProduct.getProductId());
            if (product != null) {
                vo.setProductName(product.getProductName());
                vo.setProductCode(product.getProductCode());
            }
            
            // 获取SKU信息
            ProductSku sku = productSkuService.getById(merchantProduct.getSkuId());
            if (sku != null) {
                vo.setSkuName(sku.getSkuName());
                vo.setSkuCode(sku.getSkuCode());
            }

            return vo;
        }).collect(Collectors.toList());

        voPage.setRecords(voList);

        return success(voPage);
    }

    @ApiOperation("查询商品在各商户的价格和库存")
    @GetMapping("/product/{productId}")
    public ResponseResult<List<MerchantProductVO>> getMerchantProductsByProductId(
            @ApiParam("产品ID")
            @NotNull(message = "产品ID不能为空")
            @Min(value = 1, message = "产品ID必须大于0")
            @PathVariable Long productId) {

        LambdaQueryWrapper<MerchantProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MerchantProduct::getProductId, productId)
                .eq(MerchantProduct::getStatus, 1)  // 只查询上架的商品
                .orderByAsc(MerchantProduct::getPrice);  // 按价格升序排列

        List<MerchantProduct> merchantProducts = merchantProductService.list(wrapper);

        // 封装VO对象
        List<MerchantProductVO> merchantProductVOs = merchantProducts.stream().map(merchantProduct -> {
            MerchantProductVO vo = new MerchantProductVO();
            vo.setId(merchantProduct.getId());
            vo.setMerchantId(merchantProduct.getMerchantId());
            vo.setProductId(merchantProduct.getProductId());
            vo.setSkuId(merchantProduct.getSkuId());
            vo.setPrice(merchantProduct.getPrice());
            vo.setStock(merchantProduct.getStock());
            vo.setStatus(merchantProduct.getStatus());

            // 获取商户信息
            SysUser merchant = sysUserService.getById(merchantProduct.getMerchantId());
            vo.setMerchant(merchant);

            return vo;
        }).collect(Collectors.toList());

        return success(merchantProductVOs);
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult add(@RequestBody MerchantProduct merchantProduct) {
        if (merchantProductService.saveOrUpdate(merchantProduct)) {
            return success(merchantProductService.saveOrUpdate(merchantProduct));
    } else
        return fail("已有商户产品信息");
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id) {
        return success(merchantProductService.removeById(id));
    }
}