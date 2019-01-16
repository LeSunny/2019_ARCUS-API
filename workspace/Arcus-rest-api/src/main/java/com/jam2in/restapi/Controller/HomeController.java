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
				
		JsonNode resultJson = apiService.set(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
		return resultJson;
		
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/add", method=RequestMethod.POST)
	@ResponseBody
	JsonNode add(@RequestBody ThreeSingularRequest arcusRequest){
	
		JsonNode resultJson = apiService.add(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
		return resultJson;
		
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/replace", method=RequestMethod.PATCH)
	@ResponseBody
	JsonNode replace(@RequestBody ThreeSingularRequest arcusRequest){
		
		JsonNode resultJson = apiService.replace(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
		return resultJson;
	
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/prepend", method=RequestMethod.PATCH)
	@ResponseBody
	JsonNode prepend(@RequestBody TwoRequest arcusRequest){

		JsonNode resultJson = apiService.prepend(-1, arcusRequest.getKey(), arcusRequest.getValue());
		return resultJson;

	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/append", method=RequestMethod.PATCH)
	@ResponseBody
	JsonNode append(@RequestBody TwoRequest arcusRequest){
	
		JsonNode resultNode = apiService.append(-1, arcusRequest.getKey(), arcusRequest.getValue());
		return resultNode;
	
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/set-bulk", method=RequestMethod.POST)
	@ResponseBody
	JsonNode setBulk(@RequestBody ThreeRequest arcusRequest) {

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
		
	}
	
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get", method=RequestMethod.GET)
	@ResponseBody
	JsonNode get(@RequestBody OneRequest arcusRequest){
		
		JsonNode resultJson = apiService.get(arcusRequest.getKey());
		return resultJson;
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-bulk", method=RequestMethod.GET)
	@ResponseBody
	JsonNode getBulk(@RequestBody OnePluralRequest arcusRequest) {		

		JsonNode resultJson = apiService.getBulk(arcusRequest.getKey());
		return resultJson;
	
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-attrs",method=RequestMethod.GET)
	@ResponseBody
	JsonNode getAttr(@RequestBody OneRequest arcusRequest) {

		JsonNode resultJson = apiService.getAttr(arcusRequest.getKey());
		return resultJson;				
		
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
			
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/delete",method=RequestMethod.DELETE)
	@ResponseBody
	JsonNode delete(@RequestBody OneRequest arcusRequest) {
		
		JsonNode resultJson = apiService.delete(arcusRequest.getKey());
		return resultJson;
		
	}
	
}



