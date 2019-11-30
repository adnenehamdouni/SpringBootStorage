package com.qugenx.storage.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
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
                .apis(RequestHandlerSelectors.basePackage("com.qugenx.storage"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/api/*"))
                //.paths(paths())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST API",
                "Some custom description of API.",
                "API TOS: 0.0.1-SNAPSHOT",
                "Terms of service",
                new Contact("Admin BL", "www.qugenx.com", "admin@qugenx.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    // Only select apis that matches the given Predicates.
    private Predicate<String> paths() {
        // Match all paths except /error
        return Predicates.and(
                PathSelectors.regex("/.*"),
                PathSelectors.regex("/api/*"),
                PathSelectors.regex("/starter/*"),
                Predicates.not(PathSelectors.regex("/error.*"))
        );
    }

}
