package com.novomind.ecom.ichat.customisation.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.novomind.ecom"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);

    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "IChat Customisation REST API",
                "REST API for Customising IChat display",
                "API v1",
                "Terms of service",
                new Contact("Hong Hai Le", "http://www.novomind.com", "hle@novomind.de"),
                "MIT License",
                "https://opensource.org/licenses/MIT",
                Collections.emptyList());
    }

}
