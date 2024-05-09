package com.msa.template.inventory.config.web;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	/**
	 * localhost:8080/swagger-ui.html
	 */
	@Bean
	public OpenAPI api() {

		return new OpenAPI()
			.info(new Info()
				.title("재고 Front API")
				.description("재고 Front API 명세서(스펙)")
				.version("1.0.0"))
			.components(new Components());
	}

	@Bean
	public GroupedOpenApi groupD2X() {
		return GroupedOpenApi.builder()
			.group("D2X")
			.pathsToMatch("/api/inventory/**")
			.build();
	}

	@Bean
	public GroupedOpenApi groupD2C() {
		return GroupedOpenApi.builder()
			.group("D2C")
			.pathsToMatch("/api/inventory/**")
			.build();
	}
}
