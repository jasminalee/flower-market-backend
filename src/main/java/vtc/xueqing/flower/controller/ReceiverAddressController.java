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
import vtc.xueqing.flower.entity.ReceiverAddress;
import vtc.xueqing.flower.service.ReceiverAddressService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * 收货信息表;(receiver_address)表控制层
 * @author : Xueqing
 */
// @Api(tags = "收货信息表对象功能接口")
@RestController
@RequestMapping("/receiverAddress")
public class ReceiverAddressController extends BaseController {
    @Autowired
    private ReceiverAddressService receiverAddressService;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseResult<ReceiverAddress> queryById(
            @ApiParam("地址ID") 
            @NotNull(message = "地址ID不能为空") 
            @Min(value = 1, message = "地址ID必须大于0") 
            @PathVariable Long id){
        return success(receiverAddressService.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<ReceiverAddress>> paginQuery(ReceiverAddress receiverAddress, 
                                                    @ApiParam("页码") 
                                                    @RequestParam(defaultValue = "1") 
                                                    @Min(value = 1, message = "页码必须大于0") Long current, 
                                                    @ApiParam("每页数量") 
                                                    @RequestParam(defaultValue = "10") 
                                                    @Min(value = 1, message = "每页数量必须大于0") Long size){
        LambdaQueryWrapper<ReceiverAddress> wrapper = new LambdaQueryWrapper<>(receiverAddress);
        Page<ReceiverAddress> pageResult = receiverAddressService.page(getPage(current, size), wrapper);
        
        // 处理空数据情况，确保返回空数组而不是null
        if (pageResult.getRecords() == null || pageResult.getRecords().isEmpty()) {
            pageResult.setRecords(Collections.emptyList());
        }
        
        return success(pageResult);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseResult<List<ReceiverAddress>> list(ReceiverAddress receiverAddress){
        LambdaQueryWrapper<ReceiverAddress> wrapper = new LambdaQueryWrapper<>(receiverAddress);
        return success(receiverAddressService.list(wrapper));
    }

    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Valid ReceiverAddress receiverAddress){
        return success(receiverAddressService.saveOrUpdate(receiverAddress));
    }

    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(
            @ApiParam("地址ID") 
            @NotNull(message = "地址ID不能为空") 
            @Min(value = 1, message = "地址ID必须大于0") 
            @PathVariable Long id){
        return success(receiverAddressService.removeById(id));
    }
    
    @ApiOperation("根据用户ID查询收货地址列表")
    @GetMapping("/listByUserId")
    public ResponseResult<List<ReceiverAddress>> listByUserId(
            @ApiParam("用户ID") 
            @RequestParam Long userId) {
        LambdaQueryWrapper<ReceiverAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReceiverAddress::getUserId, userId);
        return success(receiverAddressService.list(wrapper));
    }
}