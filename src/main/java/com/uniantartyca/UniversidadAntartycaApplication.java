package com.uniantartyca;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.uniantartyca.model.enums.Department;


@SpringBootApplication
public class UniversidadAntartycaApplication {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
					.addMapping("*")
					.allowedMethods("*")
					.allowedHeaders("*")
					.allowedOrigins("*");
			}
		};
	}
	
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UniversidadAntartycaApplication.class, args);
	}

	
	
}
