package vtc.xueqing.flower.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置类
 * 用于配置静态资源映射等
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射图片上传路径到实际文件系统路径
        registry.addResourceHandler("/images/uploads/**")
                .addResourceLocations("classpath:/static/images/uploads/");
    }
}