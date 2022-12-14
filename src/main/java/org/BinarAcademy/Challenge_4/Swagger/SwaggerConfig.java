package org.BinarAcademy.Challenge_4.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api(){
        String[] paths = {"/bioskop/api/**"};
        String[] packagesToScan = {"org.BinarAcademy.Challenge_4.controller"};
        return GroupedOpenApi.builder()
                .group("reservasi-ticket")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }

    @Bean
    public GroupedOpenApi auth(){
        String[] paths = {"/api/auth/**"};
        String[] packagesToScan = {"org.BinarAcademy.Challenge_4.controller"};
        return GroupedOpenApi.builder()
                .group("authentication")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("Binar Reservasi Ticket") String appTitle,
                                 @Value("v1.0.0") String appVersion){
        return new OpenAPI()
                .info(new Info().title(appTitle)
                        .description("Binar Reservasi Ticket")
                        .version(appVersion)
                        .license(new License()
                                .name("Apcahe 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("Ingwer Ludwig")
                                .email("ingwerflash@gmail.com")));
    }
}