package vtc.xueqing.flower.common;

/**
 * 响应码枚举类
 */
public enum ResponseCode {
    SUCCESS(200, "操作成功"),
    FAIL(400, "操作失败"),
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(403, "未授权"),
    NOT_FOUND(404, "接口不存在"),
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