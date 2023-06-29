package org.baps.api.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
//@EnableCaching
public class SpringBootBoilerPlateApplication {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    public static void main(final String[] args) {
        SpringApplication.run(SpringBootBoilerPlateApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {

        final String docLogoPath =
            activeProfile.equalsIgnoreCase("local") ? "/internal/assets/logo.png" : "/spring-boot-boilerplate/internal/assets/logo.png";

        final Map<String, String> logo = new HashMap<>();
        logo.put("url", docLogoPath);
        logo.put("backgroundColor", "#FFFFFF");
        logo.put("altText", "MDS Logo");

        final Map<String, Object> infoExtensions = new HashMap<>();
        infoExtensions.put("x-logo", logo);

        return new OpenAPI()
            .info(
                new Info()
                    .title("Spring Boot Boilerplate API")
                    .description(
                        "<Need description>"
                    ).extensions(infoExtensions)
            );
    }
}
