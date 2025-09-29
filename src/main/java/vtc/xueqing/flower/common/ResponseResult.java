package vtc.xueqing.flower.common;

/**
 * 全局统一响应结果类
 * @param <T> 响应数据类型
 */
public class ResponseResult<T>  {
    /**
     * 响应状态码
     */
    private Integer code;
    
    /**
     * 响应信息
     */
    private String message;
    
    /**
     * 响应数据
     */
    private T data;
    
    /**
     * 时间戳
     */
    private Long timestamp;
    
    public ResponseResult() {
        this.timestamp = System.currentTimeMillis();
    }
    
    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
    
    /**
     * 成功响应，无数据
     * @return ResponseResult<Void>
     */
    public static ResponseResult success() {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), null);
    }
    
    /**
     * 成功响应，有数据
     * @param data 响应数据
     * @param <T> 数据类型
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }
    
    /**
     * 成功响应，自定义信息和数据
     * @param message 响应信息
     * @param data 响应数据
     * @param <T> 数据类型
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), message, data);
    }
    
    /**
     * 失败响应，使用预定义的响应码
     * @param responseCode 响应码枚举
     * @return ResponseResult<Void>
     */
    public static ResponseResult<Void> fail(ResponseCode responseCode) {
        return new ResponseResult<>(responseCode.getCode(), responseCode.getMessage(), null);
    }
    
    /**
     * 失败响应，自定义错误信息
     * @param message 错误信息
     * @return ResponseResult<Void>
     */
    public static ResponseResult<String> fail(String message) {
        return new ResponseResult<>(ResponseCode.FAIL.getCode(), message, null);
    }


    public  static <T> ResponseResult<T> fail(T body) {
        return new ResponseResult<>(ResponseCode.FAIL.getCode(), "失败", body);
    }

    /**
     * 失败响应，自定义状态码和信息
     * @param code 状态码
     * @param message 错误信息
     * @return ResponseResult<Void>
     */
    public static ResponseResult<Void> fail(Integer code, String message) {
        return new ResponseResult<>(code, message, null);
    }
    
    /**
     * 失败响应，自定义状态码、信息和数据
     * @param code 状态码
     * @param message 错误信息
     * @param data 数据
     * @param <T> 数据类型
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> fail(Integer code, String message, T data) {
        return new ResponseResult<>(code, message, data);
    }
    
    // getter和setter方法
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}