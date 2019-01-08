package com.jam2in.restapi.Controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jam2in.restapi.ArcusRequest;
import com.jam2in.restapi.config.ArcusConfig;

import net.spy.memcached.ArcusClient;

/**
 * Handles requests for the application home page.
 */
@Controller
@PropertySource("classpath:/arcus.properties")
public class HomeController {
		
	@Autowired
	private ArcusConfig arcusConfig;


	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/set", method=RequestMethod.POST)
	@ResponseBody
	JsonNode set(@RequestBody ArcusRequest arcusRequest){
		/* localhost:8080/v1/test/set
		 body:
		 {
			"key" : "a",
			"expireTime" : 100,
			"obj" : "object"
		 }
		 */
		ArcusClient arcusClient;
		arcusClient= arcusConfig.defaultClient();
		
		Future<Boolean> future = null;
		boolean setSuccess = false;
		
		future = arcusClient.set(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getObj());
		
		try {
			setSuccess = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		System.out.println(setSuccess);
		
		String jsonString;
		
		if(setSuccess) {
			jsonString = "{\"result\":\"" + "SUCCESS\"}";
		}else {
			jsonString = "{\"result\":\"" + "FAILED\"}";
		}
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj=null;
		
		try {
			returnObj = mapper.readTree(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnObj;		
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/add", method=RequestMethod.POST)
	@ResponseBody
	JsonNode add(@RequestBody ArcusRequest arcusRequest){
		/*
		  localhost:8080/v1/test/add
		 
		 body:
		 {
			"key" : "a",
			"expireTime" : 100,
			"obj" : "object"
		 }
		 */
		ArcusClient arcusClient;
		arcusClient= arcusConfig.defaultClient();
		
		Future<Boolean> future = null;
		boolean setSuccess = false;
		
		future = arcusClient.add(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getObj());
		
		try {
			setSuccess = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		System.out.println(setSuccess);
		
		String jsonString;
		
		if(setSuccess) {
			jsonString = "{\"result\":\"" + "SUCCESS\"}";
		}else {
			jsonString = "{\"result\":\"" + "FAILED\"}";
		}
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj=null;
		
		try {
			returnObj = mapper.readTree(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnObj;	
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/replace", method=RequestMethod.PATCH)
	@ResponseBody
	JsonNode replace(@RequestBody ArcusRequest arcusRequest){
		/*
		  localhost:8080/v1/test/patch
		 
		 body:
		 {
			"key" : "a",
			"expireTime" : 100,
			"obj" : "object"
		 }
		 */
		ArcusClient arcusClient;
		arcusClient= arcusConfig.defaultClient();
		
		Future<Boolean> future = null;
		boolean setSuccess = false;
		
		future = arcusClient.replace(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getObj());
		
		try {
			setSuccess = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		System.out.println(setSuccess);
		
		String jsonString;
		
		if(setSuccess) {
			jsonString = "{\"result\":\"" + "SUCCESS\"}";
		}else {
			jsonString = "{\"result\":\"" + "FAILED\"}";
		}
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj=null;
		
		try {
			returnObj = mapper.readTree(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnObj;
	}
	
	//error
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/prepend", method=RequestMethod.POST)
	@ResponseBody
	JsonNode prepend(@RequestBody ArcusRequest arcusRequest){
		/*
		  localhost:8080/v1/test/prepend
		 
		 body:
		 {
			"key" : "a",
			"expireTime" : 100,
			"obj" : "object"
		 }
		 */
		ArcusClient arcusClient;
		arcusClient= arcusConfig.defaultClient();
		
		Future<Boolean> future = null;
		boolean setSuccess = false;
		
		future = arcusClient.prepend(-1, arcusRequest.getKey(), arcusRequest.getObj());
		
		try {
			setSuccess = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		System.out.println(setSuccess);
		
		String jsonString;
		
		if(setSuccess) {
			jsonString = "{\"result\":\"" + "SUCCESS\"}";
		}else {
			jsonString = "{\"result\":\"" + "FAILED\"}";
		}
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj=null;
		
		try {
			returnObj = mapper.readTree(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnObj;	
	}
	
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-get", method=RequestMethod.GET)
	@ResponseBody
	JsonNode get(@RequestBody ArcusRequest arcusRequest){
		/*
		 localhost:8080/v1/test/async-get
		 {
			"key" : "a"
		 }
		 */
		ArcusClient arcusClient;
		arcusClient = arcusConfig.defaultClient();
		
		Future<Object> future = null;
		Object value = null;
		
		future = arcusClient.asyncGet(arcusRequest.getKey());
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		System.out.println(value);
		
		String jsonString;
		
		if(value!=null) {
			jsonString = "{\"value\":\""+(String)value+"\"}";
		}else {
			jsonString = "{\"value\":\"null\"}";
		}
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj=null;
		
		try {
			returnObj = mapper.readTree(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnObj;
	}
}
