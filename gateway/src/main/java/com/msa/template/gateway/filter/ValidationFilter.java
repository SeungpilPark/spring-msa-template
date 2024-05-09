package com.msa.template.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationFilter extends AbstractGatewayFilterFactory<ValidationFilter.Config> {
	public ValidationFilter() {
		super(Config.class);
	}

	public static class Config {
		// Put the configuration properties
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			// "Authorization" 헤더 검증
			String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
			if (!"my-secret".equals(authHeader)) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
			return chain.filter(exchange);
		};
	}
}

