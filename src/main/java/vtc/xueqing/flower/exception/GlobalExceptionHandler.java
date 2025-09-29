package vtc.xueqing.flower.exception;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.common.ResponseCode;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 处理参数校验异常等通用异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理请求参数校验异常（绑定到实体对象时）
     */
    @ExceptionHandler(BindException.class)
    public ResponseResult<Void> handleBindException(BindException e) {
        log.warn("参数绑定异常: {}", e.getMessage());
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseResult.fail(ResponseCode.FAIL.getCode(), message, null);
    }

    /**
     * 处理请求参数校验异常（@Valid注解配合@RequestParam等）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("方法参数校验异常: {}", e.getMessage());
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseResult.fail(ResponseCode.FAIL.getCode(), message, null);
    }

    /**
     * 处理请求参数校验异常（直接在方法参数上使用注解）
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult<Void> handleConstraintViolationException(ConstraintViolationException e) {
        log.warn("参数校验异常: {}", e.getMessage());
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return ResponseResult.fail(ResponseCode.FAIL.getCode(), message, null);
    }

    /**
     * 处理其他未捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> handleException(Exception e) {
        log.error("系统异常: ", e);
        return ResponseResult.fail(ResponseCode.INTERNAL_SERVER_ERROR);
    }
}