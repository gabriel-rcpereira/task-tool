package com.grcp.demo.task.tool.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    OpenAPI taskToolOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Tool API")
                        .description("REST API for creating, listing, retrieving, updating, and deleting tasks.")
                        .version("v1")
                        .contact(new Contact().name("task-tool")));
    }
}
