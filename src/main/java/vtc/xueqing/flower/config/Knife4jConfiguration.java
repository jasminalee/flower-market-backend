package vtc.xueqing.flower.config;

import com.google.common.base.Predicates;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 使用 test() 替代 apply()
                .apis(requestHandler ->
                        // RequestHandlerSelectors.basePackage("vtc.xueqing.flower.controller").test(requestHandler) ||
                        //         RequestHandlerSelectors.withClassAnnotation(RestController.class).test(requestHandler) ||
                                RequestHandlerSelectors.withClassAnnotation(Api.class).test(requestHandler)
                )
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("项目API接口文档")
                .version("1.0")
                .build();
    }
}
