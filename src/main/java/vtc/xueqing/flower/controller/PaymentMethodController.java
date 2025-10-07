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
import vtc.xueqing.flower.entity.PaymentMethod;
import vtc.xueqing.flower.service.PaymentMethodService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 支付方式表;(payment_method)表控制层
 * @author : Xueqing
 */
// @Api(tags = "支付方式表对象功能接口")
@RestController
@RequestMapping("/paymentMethod")
public class PaymentMethodController extends BaseController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<PaymentMethod> queryById(@PathVariable Long id) {
        return success(paymentMethodService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<PaymentMethod>> paginQuery(PaymentMethod paymentMethod,
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size) {
        // 创建查询条件
        LambdaQueryWrapper<PaymentMethod> wrapper = new LambdaQueryWrapper<>(paymentMethod);
        
        // 执行分页查询
        Page<PaymentMethod> page = paymentMethodService.page(getPage(current, size), wrapper);
        
        return success(page);
    }

    @ApiOperation("根据用户ID查询支付方式")
    @GetMapping("/user/{userId}")
    public ResponseResult<List<PaymentMethod>> queryByUserId(@PathVariable Long userId) {
        LambdaQueryWrapper<PaymentMethod> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PaymentMethod::getUserId, userId);
        List<PaymentMethod> list = paymentMethodService.list(wrapper);
        return success(list);
    }

    @ApiOperation("查询所有支付方式")
    @GetMapping("/list")
    public ResponseResult<List<PaymentMethod>> queryAll() {
        List<PaymentMethod> list = paymentMethodService.list();
        return success(list);
    }

    @ApiOperation("新增支付方式")
    @PostMapping
    public ResponseResult<PaymentMethod> add(@RequestBody @Valid PaymentMethod paymentMethod) {
        paymentMethodService.save(paymentMethod);
        return success("创建成功", paymentMethod);
    }

    @ApiOperation("修改支付方式")
    @PutMapping
    public ResponseResult<PaymentMethod> edit(@RequestBody @Valid PaymentMethod paymentMethod) {
        paymentMethodService.updateById(paymentMethod);
        return success("更新成功", paymentMethod);
    }

    @ApiOperation("删除支付方式")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable Long id) {
        return success(paymentMethodService.removeById(id));
    }
}