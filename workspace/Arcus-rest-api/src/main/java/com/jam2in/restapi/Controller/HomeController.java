package com.jam2in.restapi.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.restapi.request.FourRequest;
import com.jam2in.restapi.request.OnePluralRequest;
import com.jam2in.restapi.request.OneRequest;
import com.jam2in.restapi.request.ThreeRequest;
import com.jam2in.restapi.request.ThreeSingularRequest;
import com.jam2in.restapi.request.TwoRequest;
import com.jam2in.restapi.Service.ApiService;
import com.jam2in.restapi.config.ArcusConfig;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.internal.CollectionFuture;
import net.spy.memcached.ops.CollectionOperationStatus;

/**
 * Handles requests for the application home page.
 */

@Controller
@PropertySource("classpath:/arcus.properties")
public class HomeController {
		
	@Autowired
	private ArcusClient arcusClient;
	
	@Resource(name="apiService")
	private ApiService apiService;
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/set", method=RequestMethod.POST)
	@ResponseBody
	JsonNode set(@RequestBody ThreeSingularRequest arcusRequest){
		/* localhost:8080/v1/test/set
		 body:
		 {
			"key" : "a",
			"expireTime" : 100,
			"obj" : "object"
		 }
		 */
		
		/*https://www.baeldung.com/jackson-deserialization
		{
			"key" : "A",
			"expireTime" : 1000000,
			"value" : "{
			"id" : "r",
			"age" : 25
			}"
		}
		 */
		
		JsonNode resultJson = apiService.set(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
		return resultJson;
		//		Future<Boolean> future = null;
//		boolean result = false;
//			
//		future = arcusClient.set(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
//		
//		try {
//			result = future.get(700L, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(result);
//		
//		String jsonString;
//		
//		if(result) {
//			jsonString = "{\"result\":\"" + "SUCCESS\"}";
//		}else {
//			jsonString = "{\"result\":\"" + "FAILED\"}";
//		}
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return returnJson;
//		
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/add", method=RequestMethod.POST)
	@ResponseBody
	JsonNode add(@RequestBody ThreeSingularRequest arcusRequest){
		/*
		  localhost:8080/v1/test/add
		 
		 body:
		 {
			"key" : "a",
			"expireTime" : 100,
			"obj" : "object"
		 }
		 */
		
		JsonNode resultJson = apiService.add(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
		return resultJson;
		
//		Future<Boolean> future = null;
//		boolean result = false;
//		
//		future = arcusClient.add(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
//		
//		try {
//			result = future.get(700L, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(result);
//		
//		String jsonString;
//		
//		if(result) {
//			jsonString = "{\"result\":\"" + "SUCCESS\"}";
//		}else {
//			jsonString = "{\"result\":\"" + "FAILED\"}";
//		}
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return returnJson;
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/replace", method=RequestMethod.PATCH)
	@ResponseBody
	JsonNode replace(@RequestBody ThreeSingularRequest arcusRequest){
		/*
		  localhost:8080/v1/test/patch
		 
		 body:
		 {
			"key" : "a",
			"expireTime" : 100,
			"obj" : "object"
		 }
		 */
		
		JsonNode resultJson = apiService.replace(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
		return resultJson;
		
//		Future<Boolean> future = null;
//		boolean result = false;
//		
//		future = arcusClient.replace(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
//		
//		try {
//			result = future.get(700L, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(result);
//		
//		String jsonString;
//		
//		if(result) {
//			jsonString = "{\"result\":\"" + "SUCCESS\"}";
//		}else {
//			jsonString = "{\"result\":\"" + "FAILED\"}";
//		}
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return returnJson;
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/prepend", method=RequestMethod.PATCH)
	@ResponseBody
	JsonNode prepend(@RequestBody TwoRequest arcusRequest){
		/*
		  localhost:8080/v1/test/prepend
		 
		 body:
		 {
			"key" : "a",
			"obj" : "object"
		 }
		 */

		JsonNode resultJson = apiService.prepend(-1, arcusRequest.getKey(), arcusRequest.getValue());
		return resultJson;
		//		Future<Boolean> future = null;
//		boolean result = false;
//		
//		future = arcusClient.prepend(-1, arcusRequest.getKey(), arcusRequest.getValue());
//		
//		try {
//			result = future.get(700L, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(result);
//		
//		String jsonString;
//		
//		if(result) {
//			jsonString = "{\"result\":\"" + "SUCCESS\"}";
//		}else {
//			jsonString = "{\"result\":\"" + "FAILED\"}";
//		}
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return returnJson;
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/append", method=RequestMethod.PATCH)
	@ResponseBody
	JsonNode append(@RequestBody TwoRequest arcusRequest){
		/*
		  localhost:8080/v1/test/append
		 
		 body:
		 {
			"key" : "a",
			"obj" : "object"
		 }
		 */
		JsonNode resultNode = apiService.append(-1, arcusRequest.getKey(), arcusRequest.getValue());
		return resultNode;
//		Future<Boolean> future = null;
//		boolean result = false;
//		
//		future = arcusClient.append(-1, arcusRequest.getKey(), arcusRequest.getValue());
//		
//		try {
//			result = future.get(700L, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(result);
//		
//		String jsonString;
//		
//		if(result) {
//			jsonString = "{\"result\":\"" + "SUCCESS\"}";
//		}else {
//			jsonString = "{\"result\":\"" + "FAILED\"}";
//		}
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return returnJson;
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/set-bulk", method=RequestMethod.POST)
	@ResponseBody
	JsonNode setBulk(@RequestBody ThreeRequest arcusRequest) {
		/*{
			"keys" : ["a", "b", "c"],
			"values" : ["1", "2", "3"],
			"expireTime" : 2000
		}
		
		{
			"key" : ["A","BA"],
			"expireTime" : 1000,
			"value" : [
				{
				"id" : "r",
				"age" : 225
				},
				{
					"id" : "r2",
					"age" : 21
				}
			]
		}
		*/

		JsonNode resultJson = null;
		
		if(arcusRequest.getValue().get(1)==null) {
			resultJson = apiService.setBulk(arcusRequest.getKey(),arcusRequest.getExpireTime(),arcusRequest.getValue().get(0));
		}else {
			Iterator<String> key = arcusRequest.getKey().iterator();
			Iterator<String> value = arcusRequest.getValue().iterator();
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			
			while(key.hasNext() && value.hasNext()) {
				paramMap.put(key.next(),value.next());
			}
			resultJson = apiService.setBulk(paramMap, arcusRequest.getExpireTime());
		}
		return resultJson;
		
//		Future<Map<String,CollectionOperationStatus>> future = null;
//		Map<String,CollectionOperationStatus> resultMap = null;
//			
//		if(arcusRequest.getValue().get(1) ==null) {
//			future = arcusClient.asyncSetBulk(arcusRequest.getKey(),arcusRequest.getExpireTime(), arcusRequest.getValue().get(0));
//		}else {
//			Iterator<String> key = arcusRequest.getKey().iterator();
//			Iterator<String> value = arcusRequest.getValue().iterator();
//			HashMap<String,Object> paramMap = new HashMap<String,Object>();
//			
//			while(key.hasNext() && value.hasNext()) {
//				paramMap.put(key.next(),value.next());
//			}
//			future = arcusClient.asyncSetBulk(paramMap, arcusRequest.getExpireTime());
//		}
//
//		try {
//			resultMap = future.get(700L, TimeUnit.MILLISECONDS);
//			System.out.println("result Map : "+resultMap);//.get("value")+"value");
//		} catch (InterruptedException | ExecutionException | TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = mapper.convertValue(resultMap, JsonNode.class);
//		
//		return returnJson;
		
		
		
	}
	
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get", method=RequestMethod.GET)
	@ResponseBody
	JsonNode get(@RequestBody OneRequest arcusRequest){
		/*
		 localhost:8080/v1/test/async-get
		 {
			"key" : "a"
		 }
		 */

		JsonNode resultJson = apiService.get(arcusRequest.getKey());
		return resultJson;
		
//		Future<Object> future = null;
//		Object value = null;
//		
//		future = arcusClient.asyncGet(arcusRequest.getKey());
//		
//		try {
//			value = future.get(700L, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(value);
//		
//		String jsonString;
//		
//		if(value!=null) {
//			jsonString = "{\"value\":"+(String)value+"}";
//		}else {
//			jsonString = "{\"value\":\"null\"}";
//		}
//		System.out.println(jsonString);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return returnJson;
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-bulk", method=RequestMethod.GET)
	@ResponseBody
	JsonNode getBulk(@RequestBody OnePluralRequest arcusRequest) {
		/*{
			"keys" : ["a", "b", "c"],
			"values" : ["1", "2", "3"],
			"expireTime" : 2000
		}
		*/
		
		JsonNode resultJson = apiService.getBulk(arcusRequest.getKey());
		return resultJson;
		
//		Future<Map<String,Object>> future = null;
//		Map<String,Object> resultMap = null;
//		
//		
//		future = arcusClient.asyncGetBulk(arcusRequest.getKey());
//		
//		try {
//			resultMap = future.get(700L, TimeUnit.MILLISECONDS);
//			System.out.println("result Map : "+resultMap);
//		} catch (InterruptedException | ExecutionException | TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		//Map to json
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode beforeJson = mapper.convertValue(resultMap, JsonNode.class);
//		System.out.println(beforeJson);
//		return beforeJson;
		
		
		
		
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-attrs",method=RequestMethod.GET)
	@ResponseBody
	JsonNode getAttr(@RequestBody OneRequest arcusRequest) {

		JsonNode resultJson = apiService.getAttr(arcusRequest.getKey());
		return resultJson;
//		CollectionFuture<CollectionAttributes> future = null;
//		CollectionAttributes result = null;
//		
//		future = arcusClient.asyncGetAttr(arcusRequest.getKey());
//		
//		try {
//			result = future.get(700L, TimeUnit.MILLISECONDS);
//			System.out.println(result);/*flags=0 expiretime=9982 type=kv*/
//		} catch (InterruptedException | TimeoutException | ExecutionException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(result.getExpireTime());
//		System.out.println(result.getType());
//		
//		String jsonString = "{\"type\" : \""+result.getType()+"\", \"expireTime\" : \""+result.getExpireTime()+"\"}";
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return returnJson;
//				
		
	}
	
	

	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/incr",method=RequestMethod.PATCH)
	@ResponseBody
	JsonNode increase(@RequestBody FourRequest arcusRequest){//@RequestBody ArcusRequest arcusRequest) {
		
		JsonNode resultJson = null;
		if(arcusRequest.getDef() == null) {
			resultJson = apiService.increase(arcusRequest.getKey(), arcusRequest.getBy());
		}else {
			resultJson = apiService.increase(arcusRequest.getKey(), arcusRequest.getBy(), arcusRequest.getDef(), arcusRequest.getExpireTime());
		}
		return resultJson;
		
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/decr",method=RequestMethod.PATCH)
	@ResponseBody
	JsonNode decrease(@RequestBody FourRequest arcusRequest) {
		JsonNode resultJson = null;
		if(arcusRequest.getDef() == null) {
			resultJson = apiService.increase(arcusRequest.getKey(), arcusRequest.getBy());
		}else {
			resultJson = apiService.increase(arcusRequest.getKey(), arcusRequest.getBy(), arcusRequest.getDef(), arcusRequest.getExpireTime());
		}
		return resultJson;
		
		
//		Future<Long> future = null;
//		long value = 0;
//		int paramFlag = 0;
//		
//		if(arcusRequest.getDef() == null) {
//			future = arcusClient.asyncDecr(arcusRequest.getKey(), arcusRequest.getBy());
//			paramFlag = 0;
//		}else {
//			future = arcusClient.asyncDecr(arcusRequest.getKey(), arcusRequest.getBy(), arcusRequest.getDef(), arcusRequest.getExpireTime());
//			paramFlag = 1;
//		}
//		
//		try {
//			value = future.get(700L, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException | ExecutionException | TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		String jsonString;
//				
//		if(value==-1 && paramFlag == 0) {
//			jsonString = "{\"value\":\"null\"}";
//		}else {
//			jsonString = "{\"value\":\""+value+"\"}";
//		}
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return returnJson;
//		
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/delete",method=RequestMethod.DELETE)
	@ResponseBody
	JsonNode delete(@RequestBody OneRequest arcusRequest) {
		
		JsonNode resultJson = apiService.delete(arcusRequest.getKey());
		return resultJson;
//		Future<Boolean> future = null;
//		boolean result = false;
//		
//		future = arcusClient.delete(arcusRequest.getKey());
//		
//		try {
//			result = future.get(700L, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException | ExecutionException | TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		String jsonString;
//		
//		if(result) {
//			jsonString = "{\"result\":\"SUCCESS\"}";
//		}else {
//			jsonString = "{\"result\":\"FAILED\"}";
//		}
//	
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return returnJson;
//		
	}
	
}



