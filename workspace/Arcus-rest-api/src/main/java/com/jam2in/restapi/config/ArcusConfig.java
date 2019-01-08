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
	
	Environment env;
	ArcusClient arcusClient;
	
	@Value("${arcus.address}")
	private String address;
	@Value("${arcus.serviceCode}")
	private String serviceCode;
	
	
	@PostConstruct
	public ArcusClient defaultClient() {
		
		//arcusAdmin "192.168.0.155"�� vmware�� ip
		//arcusClient = ArcusClient.createArcusClient("192.168.0.155","test", new ConnectionFactoryBuilder());
//		arcusClient = ArcusClient.createArcusClient(env.getProperty("arcus.address"),env.getProperty("arcus.serviceCode"), new ConnectionFactoryBuilder());
		arcusClient = ArcusClient.createArcusClient(address,serviceCode, new ConnectionFactoryBuilder());
		
		return arcusClient;
	}
	
	
//	@PreDestroy
//	public ArcusClient closeClient() {
//		arcusClient.shutdown();
//		arcusClient=null;
//		return arcusClient;
//	}
}