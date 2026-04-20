package com.veterinaria.historialclinico.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .info(new Info()
	                    .title("API Historial Clínico Veterinario")
	                    .version("1.0")
	                    .description("""
	                        API REST para la gestión de:
	                        - Usuarios
	                        - Mascotas
	                        - Historias clínicas
	                        - Detalles clínicos

	                        Proyecto desarrollado como prueba técnica backend + frontend.
	                    """));
	}
}