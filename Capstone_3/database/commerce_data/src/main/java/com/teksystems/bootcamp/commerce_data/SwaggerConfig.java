package com.teksystems.bootcamp.commerce_data;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConditionalOnProperty(name = "app.api.swagger.enable", havingValue = "true", matchIfMissing = false)
public class SwaggerConfig {
    private String version;
    private String title;
    private String description;
    private String basePackage;
    private String contactName;
    private String contactEmail;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Commerce API App by Team RabBITs")
                .description("Commerece App REST API")
                .contact(new Contact("Team RabBITS", "N/A", "N/A"))
                .license("TekSystems Project")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .version("0.3.0")
                .build();
    }
}
