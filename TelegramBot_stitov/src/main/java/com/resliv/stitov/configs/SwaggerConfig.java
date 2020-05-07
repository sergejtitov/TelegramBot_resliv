package com.resliv.stitov.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(generateApiInfo()).directModelSubstitute(Timestamp.class, Long.class);
    }

    private ApiInfo generateApiInfo() {
        return new ApiInfo(                "demo", "demo.", "Version 1.0", "urn:tos", ApiInfo.DEFAULT_CONTACT, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Telegram bot REST API")
                .description("Telegram bot REST API")
                .contact(new Contact("Titov Sergej", "www.stitov.resliv.com", "sergejtitov1990@mail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
