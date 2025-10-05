package vtc.xueqing.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtc.xueqing.flower.entity.Product;
import vtc.xueqing.flower.entity.ProductSku;
import vtc.xueqing.flower.entity.ShoppingCart;
import vtc.xueqing.flower.entity.SysUser;
import vtc.xueqing.flower.mapper.ProductMapper;
import vtc.xueqing.flower.mapper.ProductSkuMapper;
import vtc.xueqing.flower.mapper.ShoppingCartMapper;
import vtc.xueqing.flower.mapper.SysUserMapper;
import vtc.xueqing.flower.service.ShoppingCartService;
import vtc.xueqing.flower.vo.ShoppingCartVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private ProductSkuMapper productSkuMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<ShoppingCartVO> listShoppingCartWithProductInfo(Long userId) {
        // 查询用户的购物车项
        List<ShoppingCart> shoppingCarts = this.lambdaQuery()
                .eq(ShoppingCart::getUserId, userId)
                .list();
        
        // 获取所有相关的产品ID和SKU ID
        List<Long> productIds = shoppingCarts.stream()
                .map(ShoppingCart::getProductId)
                .distinct()
                .collect(Collectors.toList());
        
        List<Long> skuIds = shoppingCarts.stream()
                .map(ShoppingCart::getSkuId)
                .distinct()
                .collect(Collectors.toList());
        
        List<Long> merchantIds = shoppingCarts.stream()
                .map(ShoppingCart::getMerchantId)
                .distinct()
                .collect(Collectors.toList());
        
        // 批量查询产品、SKU和商户信息
        List<Product> products = productMapper.selectBatchIds(productIds);
        List<ProductSku> productSkus = productSkuMapper.selectBatchIds(skuIds);
        List<SysUser> merchants = sysUserMapper.selectBatchIds(merchantIds);
        
        // 转换为Map便于查找
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
        
        Map<Long, ProductSku> skuMap = productSkus.stream()
                .collect(Collectors.toMap(ProductSku::getId, sku -> sku));
        
        Map<Long, SysUser> merchantMap = merchants.stream()
                .collect(Collectors.toMap(SysUser::getId, merchant -> merchant));
        
        // 构建VO列表
        List<ShoppingCartVO> result = new ArrayList<>();
        for (ShoppingCart cart : shoppingCarts) {
            ShoppingCartVO vo = new ShoppingCartVO();
            BeanUtils.copyProperties(cart, vo);
            
            // 设置产品名称
            Product product = productMap.get(cart.getProductId());
            if (product != null) {
                vo.setProductName(product.getProductName());
                vo.setMainImage(product.getMainImage());
            }
            
            // 设置SKU名称
            ProductSku sku = skuMap.get(cart.getSkuId());
            if (sku != null) {
                vo.setSkuName(sku.getSkuName());
            }
            
            // 设置商户名称
            SysUser merchant = merchantMap.get(cart.getMerchantId());
            if (merchant != null) {
                vo.setMerchantName(merchant.getNickname());
            }
            
            result.add(vo);
        }
        
        return result;
    }
    
    @Override
    public Page<ShoppingCartVO> pageShoppingCartWithProductInfo(Long current, Long size, ShoppingCart shoppingCart) {
        // 分页查询购物车项
        Page<ShoppingCart> page = new Page<>(current, size);
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>(shoppingCart);
        Page<ShoppingCart> shoppingCartPage = this.page(page, wrapper);
        
        // 获取所有相关的产品ID和SKU ID
        List<Long> productIds = shoppingCartPage.getRecords().stream()
                .map(ShoppingCart::getProductId)
                .distinct()
                .collect(Collectors.toList());
        
        List<Long> skuIds = shoppingCartPage.getRecords().stream()
                .map(ShoppingCart::getSkuId)
                .distinct()
                .collect(Collectors.toList());
        
        List<Long> merchantIds = shoppingCartPage.getRecords().stream()
                .map(ShoppingCart::getMerchantId)
                .distinct()
                .collect(Collectors.toList());
        
        // 批量查询产品、SKU和商户信息
        List<Product> products = productMapper.selectBatchIds(productIds);
        List<ProductSku> productSkus = productSkuMapper.selectBatchIds(skuIds);
        List<SysUser> merchants = sysUserMapper.selectBatchIds(merchantIds);
        
        // 转换为Map便于查找
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
        
        Map<Long, ProductSku> skuMap = productSkus.stream()
                .collect(Collectors.toMap(ProductSku::getId, sku -> sku));
        
        Map<Long, SysUser> merchantMap = merchants.stream()
                .collect(Collectors.toMap(SysUser::getId, merchant -> merchant));
        
        // 构建VO列表
        List<ShoppingCartVO> result = new ArrayList<>();
        for (ShoppingCart cart : shoppingCartPage.getRecords()) {
            ShoppingCartVO vo = new ShoppingCartVO();
            BeanUtils.copyProperties(cart, vo);
            
            // 设置产品名称
            Product product = productMap.get(cart.getProductId());
            if (product != null) {
                vo.setProductName(product.getProductName());
                vo.setMainImage(product.getMainImage());
            }
            
            // 设置SKU名称
            ProductSku sku = skuMap.get(cart.getSkuId());
            if (sku != null) {
                vo.setSkuName(sku.getSkuName());
            }
            
            // 设置商户名称
            SysUser merchant = merchantMap.get(cart.getMerchantId());
            if (merchant != null) {
                vo.setMerchantName(merchant.getNickname());
            }
            
            result.add(vo);
        }
        
        // 构建返回的分页对象
        Page<ShoppingCartVO> voPage = new Page<>(current, size, shoppingCartPage.getTotal());
        voPage.setRecords(result);
        
        return voPage;
    }
    
    @Override
    public ShoppingCart addProductToCart(ShoppingCart shoppingCart) {
        // 检查是否已存在相同的商户ID和产品ID的购物车记录
        ShoppingCart existingCart = this.lambdaQuery()
                .eq(ShoppingCart::getUserId, shoppingCart.getUserId())
                .eq(ShoppingCart::getMerchantId, shoppingCart.getMerchantId())
                .eq(ShoppingCart::getProductId, shoppingCart.getProductId())
                .eq(ShoppingCart::getSkuId, shoppingCart.getSkuId())
                .one();
        
        if (existingCart != null) {
            // 如果存在，则数量加1
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            existingCart.setUpdateTime(LocalDateTime.now());
            this.updateById(existingCart);
            return existingCart;
        } else {
            // 如果不存在，则新增记录
            shoppingCart.setQuantity(shoppingCart.getQuantity() == null ? 1 : shoppingCart.getQuantity());
            shoppingCart.setStatus(1); // 默认有效状态
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCart.setUpdateTime(LocalDateTime.now());
            this.save(shoppingCart);
            return shoppingCart;
        }
    }
}