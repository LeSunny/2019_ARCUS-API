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
import com.jam2in.restapi.response.ArcusSuccessResponse;
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
	
	@Resource(name="apiService")
	private ApiService apiService;
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/set", method=RequestMethod.POST)
	@ResponseBody // 응답 시 return 되는 것을 자동으로 json으로 바꾸어 줌
	ArcusSuccessResponse set(@RequestBody ThreeSingularRequest arcusRequest){				
		return apiService.set(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
		
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/add", method=RequestMethod.POST)
	@ResponseBody
	ArcusSuccessResponse add(@RequestBody ThreeSingularRequest arcusRequest){
		return apiService.add(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());		
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/replace", method=RequestMethod.PATCH)
	@ResponseBody
	ArcusSuccessResponse replace(@RequestBody ThreeSingularRequest arcusRequest){
		return apiService.replace(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/prepend", method=RequestMethod.PATCH)
	@ResponseBody
	ArcusSuccessResponse prepend(@RequestBody TwoRequest arcusRequest){
		return apiService.prepend(-1, arcusRequest.getKey(), arcusRequest.getValue());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/append", method=RequestMethod.PATCH)
	@ResponseBody
	ArcusSuccessResponse append(@RequestBody TwoRequest arcusRequest){
		return apiService.append(-1, arcusRequest.getKey(), arcusRequest.getValue());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/set-bulk", method=RequestMethod.POST)
	@ResponseBody
	ArcusSuccessResponse setBulk(@RequestBody ThreeRequest arcusRequest) {
		if(arcusRequest.getValue().get(1)==null) {
			return apiService.setBulk(arcusRequest.getKey(),arcusRequest.getExpireTime(),arcusRequest.getValue().get(0));
		}else {
			Iterator<String> key = arcusRequest.getKey().iterator();
			Iterator<String> value = arcusRequest.getValue().iterator();
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			
			while(key.hasNext() && value.hasNext()) {
				paramMap.put(key.next(),value.next());
			}
			return apiService.setBulk(paramMap, arcusRequest.getExpireTime());
		}
	}
	
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get", method=RequestMethod.GET)
	@ResponseBody
	ArcusSuccessResponse get(@RequestBody OneRequest arcusRequest){
		return apiService.get(arcusRequest.getKey());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-bulk", method=RequestMethod.GET)
	@ResponseBody
	ArcusSuccessResponse getBulk(@RequestBody OnePluralRequest arcusRequest) {		
		return apiService.getBulk(arcusRequest.getKey());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-attrs",method=RequestMethod.GET)
	@ResponseBody
	ArcusSuccessResponse getAttr(@RequestBody OneRequest arcusRequest) {
		return apiService.getAttr(arcusRequest.getKey());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/incr",method=RequestMethod.PATCH)
	@ResponseBody
	ArcusSuccessResponse increase(@RequestBody FourRequest arcusRequest){//@RequestBody ArcusRequest arcusRequest) {
		if(arcusRequest.getDef() == null) {
			return apiService.increase(arcusRequest.getKey(), arcusRequest.getBy());
		}else {
			return apiService.increase(arcusRequest.getKey(), arcusRequest.getBy(), arcusRequest.getDef(), arcusRequest.getExpireTime());
		}
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/decr",method=RequestMethod.PATCH)
	@ResponseBody
	ArcusSuccessResponse decrease(@RequestBody FourRequest arcusRequest) {
		if(arcusRequest.getDef() == null) {
			return apiService.increase(arcusRequest.getKey(), arcusRequest.getBy());
		}else {
			return apiService.increase(arcusRequest.getKey(), arcusRequest.getBy(), arcusRequest.getDef(), arcusRequest.getExpireTime());
		}	
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/delete",method=RequestMethod.DELETE)
	@ResponseBody
	ArcusSuccessResponse delete(@RequestBody OneRequest arcusRequest) throws InterruptedException, ExecutionException, TimeoutException {
		return apiService.delete(arcusRequest.getKey());
	}
	
}



