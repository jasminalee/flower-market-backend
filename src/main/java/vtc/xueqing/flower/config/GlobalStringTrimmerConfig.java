package vtc.xueqing.flower.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Configuration
@RestControllerAdvice
public class GlobalStringTrimmerConfig {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 第二个参数 true 表示空字符串会被转换为 null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}