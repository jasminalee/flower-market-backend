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
import vtc.xueqing.flower.entity.MerchantProduct;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.service.MerchantProductService;
import vtc.xueqing.flower.service.SysUserService;
import vtc.xueqing.flower.vo.MerchantProductVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商户产品关联表;(merchant_product)表控制层
 * @author : Xueqing
 */
// @Api(tags = "商户产品关联表对象功能接口")
@RestController
@RequestMapping("/merchantProduct")
public class MerchantProductController extends BaseController {
    @Autowired
    private MerchantProductService merchantProductService;
    
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<MerchantProduct> queryById(
            @ApiParam("商户产品ID") 
            @NotNull(message = "商户产品ID不能为空") 
            @Min(value = 1, message = "商户产品ID必须大于0") 
            @PathVariable Long id){
        return success(merchantProductService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<MerchantProduct>> paginQuery(MerchantProduct merchantProduct, 
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        LambdaQueryWrapper<MerchantProduct> wrapper = new LambdaQueryWrapper<>(merchantProduct);
        return success(merchantProductService.page(getPage(current, size), wrapper));
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
    public ResponseResult<Boolean> add(@RequestBody MerchantProduct merchantProduct){
        return success(merchantProductService.saveOrUpdate(merchantProduct));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id){
        return success(merchantProductService.removeById(id));
    }
}