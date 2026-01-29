package com.ordermanagement.api_gatewayfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

	@Component
	public class LoggingFilter implements GlobalFilter, Ordered {

	    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	    public LoggingFilter() {
	        log.info("LoggingFilter loaded");
	    }

	    @Override
	    public Mono<Void> filter(ServerWebExchange exchange,
	                             GatewayFilterChain chain) {

	        log.info("Gateway Request Path: {}",
	                exchange.getRequest().getURI().getPath());

	        return chain.filter(exchange);
	    }

	    @Override
	    public int getOrder() {
	        return -1;
	    }
	
}
