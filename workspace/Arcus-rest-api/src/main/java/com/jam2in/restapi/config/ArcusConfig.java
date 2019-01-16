package com.jam2in.restapi.config;

import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;

//import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import net.spy.memcached.ArcusClient;

import net.spy.memcached.ConnectionFactoryBuilder;

@Configuration
//@PropertySource(value = "classpath:arcus.properties")
public class ArcusConfig {
	
	@Value("${arcus.address}")
	private String address;
	@Value("${arcus.serviceCode}")
	private String serviceCode;
	
	@Bean(destroyMethod = "shutdown")
	public ArcusClient arcusClient() {
		ArcusClient arcusClient = ArcusClient.createArcusClient(address,serviceCode, new ConnectionFactoryBuilder());
		
		return arcusClient;
	}
	
}