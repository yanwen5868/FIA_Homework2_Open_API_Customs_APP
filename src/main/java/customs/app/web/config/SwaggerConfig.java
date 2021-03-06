package customs.app.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket api() {
//        String protocol =  "http";
        return new Docket(DocumentationType.SWAGGER_2)
//                .protocols(Sets.newHashSet(protocol))
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("財政部關務署政府開放資料 提供 Open API Service")
                .description("財政部關務署政府開放資料 提供資料應用方之開發人員使用API Service取得開放資料加值及應用")
                .version("1.0")
                .build();
                
    }
    
    

}
