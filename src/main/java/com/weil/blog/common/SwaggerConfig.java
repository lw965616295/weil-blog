package com.weil.blog.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Author weil
 * @Description //配置
 * @Date 2022/6/15 13:53
 * @Version 1.0.0
 **/
@Slf4j
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfig {
    @Bean
    public Docket getDocket() {
        log.info(">>>>>>>>>   swagger 开启！");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("weil_blog System")
                        .description("weil_blog系统API ")
                        .version("1.0.0")
//                        .contact(new Contact("weil","blog.net","aaa@gmail.com"))
//                        .license("The Apache License")
//                        .licenseUrl("http://www.baidu.com")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.weil.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
