package com.kukilej.springdataredisdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build()
                .apiInfo(metaData());
    }

    /**
     * Customization of default values in Swagger response.
     *
     * @return
     */
    private ApiInfo metaData() {
        return new ApiInfo(
                "College Admissions",
                "Contains business logic for college admissions related functionality",
                "1.0",
                null,
                new Contact("", "", ""),
                null,
                null,
                new ArrayList());
    }

}
