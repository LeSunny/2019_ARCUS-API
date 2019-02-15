package com.jam2in.httpapi.Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jam2in.httpapi.Service.ApiService;
import com.jam2in.httpapi.request.FourRequest;
import com.jam2in.httpapi.request.OnePluralRequest;
import com.jam2in.httpapi.request.OneRequest;
import com.jam2in.httpapi.request.ThreeRequest;
import com.jam2in.httpapi.request.ThreeSingularRequest;
import com.jam2in.httpapi.request.TwoRequest;
import com.jam2in.httpapi.response.ArcusSuccessResponse;

/**
 * Handles requests for the application home page.
 */

@Controller
@PropertySource("classpath:/arcus.properties")
public class HomeController {
	
	@Resource(name="apiService")
	private ApiService apiService;
	
	protected Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/set", method=RequestMethod.POST)
	@ResponseBody // ���� �� return �Ǵ� ���� �ڵ����� json���� �ٲپ� ��
	ArcusSuccessResponse set(@RequestBody ThreeSingularRequest arcusRequest){	
//		log.info(arcusRequest.getKey());
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
	
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get", method=RequestMethod.POST)
	@ResponseBody
	ArcusSuccessResponse get(@RequestBody OneRequest arcusRequest){
		//log.info("get command - "+arcusRequest.getKey());
		return apiService.get(arcusRequest.getKey());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-bulk", method=RequestMethod.POST)
	@ResponseBody
	ArcusSuccessResponse getBulk(@RequestBody OnePluralRequest arcusRequest) {		
		return apiService.getBulk(arcusRequest.getKey());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-attrs",method=RequestMethod.POST)
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


