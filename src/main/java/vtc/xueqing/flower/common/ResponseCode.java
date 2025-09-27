package vtc.xueqing.flower.common;

/**
 * 响应码枚举类
 */
public enum ResponseCode {
    
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    
    /**
     * 失败
     */
    FAIL(400, "操作失败"),
    
    /**
     * 未认证
     */
    UNAUTHORIZED(401, "未认证"),
    
    /**
     * 未授权
     */
    FORBIDDEN(403, "未授权"),
    
    /**
     * 接口不存在
     */
    NOT_FOUND(404, "接口不存在"),
    
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");
    
    private final Integer code;
    private final String message;
    
    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
}