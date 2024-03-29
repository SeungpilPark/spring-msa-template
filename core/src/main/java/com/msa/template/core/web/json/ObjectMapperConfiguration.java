package com.msa.template.core.web.json;

import com.msa.template.core.converter.LocalDateJsonConverter;
import com.msa.template.core.converter.LocalDateTimeJsonConverter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class ObjectMapperConfiguration {

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
			.registerModule(javaTimeModule())
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	private SimpleModule javaTimeModule() {
		return new JavaTimeModule()
			.addSerializer(LocalDateTime.class, new LocalDateTimeJsonConverter.Serializer())
			.addDeserializer(LocalDateTime.class, new LocalDateTimeJsonConverter.Deserializer())
			.addSerializer(LocalDate.class, new LocalDateJsonConverter.Serializer())
			.addDeserializer(LocalDate.class, new LocalDateJsonConverter.Deserializer());
	}
}
