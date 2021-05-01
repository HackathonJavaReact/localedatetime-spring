package com.spring.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
@ServletComponentScan
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public ObjectMapper serializingObjectMapper() {
		JavaTimeModule module = new JavaTimeModule();
		LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		module.addDeserializer(LocalDateTime.class, deserializer);
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
		.modules(module)
		.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
		.build();
		return objectMapper;
	}

}
