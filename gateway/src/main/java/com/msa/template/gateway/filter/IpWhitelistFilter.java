package com.msa.template.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class IpWhitelistFilter extends AbstractGatewayFilterFactory<IpWhitelistFilter.Config> {
	private final List<String> whiteIpList = List.of("192.168.1.1", "192.168.1.2"); // 화이트리스트 IP

	public IpWhitelistFilter() {
		super(Config.class);
	}

	public static class Config {
		// 설정 속성 추가 가능
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			String ip = extractClientIp(exchange);
			if (ip == null || !whiteIpList.contains(ip)) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
			return chain.filter(exchange);
		};
	}

	private String extractClientIp(ServerWebExchange exchange) {
		// 클라이언트의 IP 주소를 추출
		return exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
	}
}
