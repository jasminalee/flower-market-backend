package vtc.xueqing.flower.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vtc.xueqing.flower.common.ResponseResult;

/**
 * 通用基础控制器，封装常用的CRUD操作
 */
public class BaseController {

    /**
     * 获取分页对象，可直接用于service.page()
     *
     * @param current 当前页码
     * @param size    每页大小
     * @return Page对象
     */
    protected <T> Page<T> getPage(Long current, Long size) {
        return new Page<>(current, size);
    }

    /**
     * 成功响应，无数据
     *
     * @return ResponseResult<Void>
     */
    protected ResponseResult<Void> success() {
        return ResponseResult.success();
    }

    /**
     * 成功响应，有数据
     *
     * @param data 响应数据
     * @return ResponseResult<T>
     */
    protected <T> ResponseResult<T> success(T data) {
        return ResponseResult.success(data);
    }

    /**
     * 成功响应，自定义信息和数据
     *
     * @param message 响应信息
     * @param data    响应数据
     * @return ResponseResult<T>
     */
    protected <T> ResponseResult<T> success(String message, T data) {
        return ResponseResult.success(message, data);
    }

    /**
     * 失败响应，使用预定义的响应码
     *
     * @param responseCode 响应码枚举
     * @return ResponseResult<Void>
     */
    protected ResponseResult<Void> fail(vtc.xueqing.flower.common.ResponseCode responseCode) {
        return ResponseResult.fail(responseCode);
    }

    /**
     * 失败响应，自定义错误信息
     *
     * @param message 错误信息
     * @return ResponseResult<Void>
     */
    protected ResponseResult<Void> fail(String message) {
        return ResponseResult.fail(message);
    }


    /**
     * 失败响应，自定义错误信息
     *
     * @return ResponseResult<T>
     */
    protected <T> ResponseResult<T> fail(T obj) {
        return ResponseResult.fail(obj);
    }

    /**
     * 失败响应，自定义状态码和信息
     *
     * @param code    状态码
     * @param message 错误信息
     * @return ResponseResult<Void>
     */
    protected ResponseResult<Void> fail(Integer code, String message) {
        return ResponseResult.fail(code, message);
    }
}