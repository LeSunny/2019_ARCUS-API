package com.jam2in.httpapi.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jam2in.httpapi.Service.ApiService;
import com.jam2in.httpapi.request.BopRequest;
import com.jam2in.httpapi.request.FourRequest;
import com.jam2in.httpapi.request.OnePluralRequest;
import com.jam2in.httpapi.request.OneRequest;
import com.jam2in.httpapi.request.ThreeRequest;
import com.jam2in.httpapi.request.ThreeSingularRequest;
import com.jam2in.httpapi.request.TwoRequest;
import com.jam2in.httpapi.response.ArcusBopBoolResponse;
import com.jam2in.httpapi.response.ArcusBopInsertBulkResponse;
import com.jam2in.httpapi.response.ArcusBopNotBoolResponse;
import com.jam2in.httpapi.response.ArcusBopTrimmedResponse;
import com.jam2in.httpapi.response.ArcusLongSuccessResponse;
import com.jam2in.httpapi.response.ArcusSetBulkSuccessResponse;
import com.jam2in.httpapi.response.ArcusSuccessResponse;

import net.spy.memcached.collection.ElementFlagFilter.BitWiseOperands;
import net.spy.memcached.internal.CollectionFuture;

/**
 * Handles requests for the application home page.
 */
@Controller
@PropertySource("classpath:/arcus.properties")
public class HomeController {
	
	@Resource(name="apiService")
	private ApiService apiService;
	
	//protected Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/set", method=RequestMethod.POST)
	@ResponseBody // automatically changes the return value to json when responding.
	ArcusSuccessResponse set(@RequestBody ThreeSingularRequest arcusRequest){	
/*
 
    "key": "a",
    "expireTime": "10000",
    "value": 8
} 
*/	return apiService.set(arcusRequest.getKey(), arcusRequest.getExpireTime(), arcusRequest.getValue());
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/set-bulk", method=RequestMethod.POST)
	@ResponseBody
	ArcusSetBulkSuccessResponse setBulk(@RequestBody ThreeRequest arcusRequest) {
		/*
 {
    "key": ["a","b","c"],
    "expireTime": "10000",
    "value": [1, "bb", "finally"]
}
=> {"result":{a=1, b=bb, c=finally}}
{
    "key": ["a","b","c"],
    "expireTime": "10000",
    "value": [1, "bb", {"kee" : "a"}]
}
=> {"result":{a=1, b=bb, c={kee=a}}}
{
    "key": ["a","b","c"],
    "expireTime": "10000",
	"value": { "id": 200, "name": 123123 } 
}
=> {"result":{a=200, b=123123}}

{
    "key": ["b","c","d"],
    "expireTime": "10000",
   	"value": 8
} 
=> {"result":{b=8, c=8, d=8}}
 */
		//object
    	//log.info("request value type : "+arcusRequest.getValue().getClass());
    	if(arcusRequest.getValue() instanceof ArrayList)
    	{	
    		List<Object> listValue = (List<Object>)arcusRequest.getValue();
    		Iterator<String> key = arcusRequest.getKey().iterator();
			Iterator<Object> value = listValue.iterator();
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			
			while(key.hasNext() && value.hasNext()) {
				paramMap.put(key.next(),value.next());
			}
	    	//log.info("request map : "+paramMap);
			return apiService.setBulk(paramMap, arcusRequest.getExpireTime());    	
    	}else {
    		//log.info("request value : "+arcusRequest.getValue());
    		return apiService.setBulk(arcusRequest.getKey(),arcusRequest.getExpireTime(),arcusRequest.getValue());
    	}
   	}
	
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get", method=RequestMethod.POST)
	@ResponseBody
	ArcusSuccessResponse get(@RequestBody OneRequest arcusRequest){
		return apiService.get(arcusRequest.getKey());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-bulk", method=RequestMethod.POST)
	@ResponseBody
	ArcusSuccessResponse getBulk(@RequestBody OnePluralRequest arcusRequest) {		
		return apiService.getBulk(arcusRequest.getKey());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/get-attr",method=RequestMethod.POST)
	@ResponseBody
	ArcusSuccessResponse getAttr(@RequestBody OneRequest arcusRequest) {
		/*
{
    "key": "c"
}
=> {"result":flags=0 expiretime=9686 type=kv}
		 */
		return apiService.getAttr(arcusRequest.getKey());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/incr",method=RequestMethod.PATCH)
	@ResponseBody
	ArcusLongSuccessResponse increase(@RequestBody FourRequest arcusRequest){//@RequestBody ArcusRequest arcusRequest) {
		if(arcusRequest.getDef() == null) {
			/*
{
    "key": "a",
    "by" : 4
} 
			 */
			return apiService.increase(arcusRequest.getKey(), arcusRequest.getBy());
		}else {
			return apiService.increase(arcusRequest.getKey(), arcusRequest.getBy(), arcusRequest.getDef(), arcusRequest.getExpireTime());
		}
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/decr",method=RequestMethod.PATCH)
	@ResponseBody
	ArcusLongSuccessResponse decrease(@RequestBody FourRequest arcusRequest) {
		if(arcusRequest.getDef() == null) {
			return apiService.decrease(arcusRequest.getKey(), arcusRequest.getBy());
		}else {
			return apiService.decrease(arcusRequest.getKey(), arcusRequest.getBy(), arcusRequest.getDef(), arcusRequest.getExpireTime());
		}	
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/delete",method=RequestMethod.DELETE)
	@ResponseBody
	ArcusSuccessResponse delete(@RequestBody OneRequest arcusRequest) throws InterruptedException, ExecutionException, TimeoutException {
		return apiService.delete(arcusRequest.getKey());
	}

	/////////B+Tree
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-create",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopBoolResponse bopCreate(@RequestBody BopRequest arcusRequest)  throws IllegalStateException,TimeoutException, InterruptedException, ExecutionException, NullPointerException {
		return apiService.bopCreate(arcusRequest.getKey(), arcusRequest.getValueType(), arcusRequest.getAttributes());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-insert",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopBoolResponse bopInsert(@RequestBody BopRequest arcusRequest) throws IllegalStateException, TimeoutException, InterruptedException, ExecutionException{
		return apiService.bopInsert(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.geteFlag(), arcusRequest.getValue(), arcusRequest.getAttributesForCreate());
	}
	

	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-piped-insert-bulk",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopInsertBulkResponse bopPipedInsertBulk(@RequestBody BopRequest arcusRequest) throws IllegalStateException, TimeoutException, InterruptedException, ExecutionException{
		return apiService.bopPipedInsertBulk(arcusRequest.getKey(), arcusRequest.getElementsWithMap(), arcusRequest.getAttributesForCreate());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-insert-bulk",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopInsertBulkResponse bopInsertBulk(@RequestBody BopRequest arcusRequest) throws IllegalStateException, TimeoutException, InterruptedException, ExecutionException{
		return apiService.bopInsertBulk(arcusRequest.getKeyList(), arcusRequest.getBkey(), arcusRequest.geteFlag(), arcusRequest.getValue(), arcusRequest.getAttributesForCreate());
	}
	
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-insert-and-get-trimmed",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopTrimmedResponse bopInsertAndGetTrimmed(@RequestBody BopRequest arcusRequest) throws Exception{
		return apiService.bopInsertAndGetTrimmed(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.geteFlag(), arcusRequest.getValue(), arcusRequest.getAttributesForCreate());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-upsert", method=RequestMethod.POST)
	@ResponseBody
	ArcusBopBoolResponse bopUpsert(@RequestBody BopRequest arcusRequest) throws IllegalStateException, TimeoutException, InterruptedException, ExecutionException{
		return apiService.bopUpsert(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.geteFlag(), arcusRequest.getValue(), arcusRequest.getAttributesForCreate());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-update",method=RequestMethod.PATCH)
	@ResponseBody
	ArcusBopBoolResponse bopUpdate(@RequestBody BopRequest arcusRequest)  throws IllegalStateException, TimeoutException, InterruptedException, ExecutionException{
		return apiService.bopUpdate(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.geteFlagUpdate(), arcusRequest.getFlag(), arcusRequest.getBitOp(), arcusRequest.geteFlagOffset(), arcusRequest.getValue());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-delete",method=RequestMethod.DELETE)
	@ResponseBody
	ArcusBopBoolResponse bopDelete(@RequestBody BopRequest arcusRequest) {
		if(arcusRequest.getFrom() != null) {
			//return apiService.bopDelete(arcusRequest.getKey(), arcusRequest.getFrom(), arcusRequest.geteFlagFilter(), arcusRequest.getCompValue(), arcusRequest.getTo(), arcusRequest.getCount(), arcusRequest.getDropIfEmpty());
			return apiService.bopDelete(arcusRequest.getKey(),arcusRequest.getFrom(), arcusRequest.getTo(), arcusRequest.getCount(), arcusRequest.getDropIfEmpty());
		}else {
			//return apiService.bopDelete(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.geteFlagFilter(), arcusRequest.getCompValue(), arcusRequest.getDropIfEmpty());
			return apiService.bopDelete(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.getDropIfEmpty());
		}
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-incr",method=RequestMethod.PATCH)
	@ResponseBody
	ArcusBopNotBoolResponse bopIncr(@RequestBody BopRequest arcusRequest) {
		if(arcusRequest.getInitial() != null) {
			return apiService.bopIncr(arcusRequest.getKey(), arcusRequest.getSubkey(), arcusRequest.getBy(), arcusRequest.getInitial(), arcusRequest.geteFlag());
		}else {
			return apiService.bopIncr(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.getBy());
		}
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-decr",method=RequestMethod.PATCH)
	@ResponseBody
	ArcusBopNotBoolResponse bopDecr(@RequestBody BopRequest arcusRequest) {
		if(arcusRequest.getInitial() != null) {
			return apiService.bopDecr(arcusRequest.getKey(), arcusRequest.getSubkey(), arcusRequest.getBy(), arcusRequest.getInitial(), arcusRequest.geteFlag());
		}else {
			return apiService.bopDecr(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.getBy());
		}
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-get-item-count",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopNotBoolResponse bopGetItemCount(@RequestBody BopRequest arcusRequest) {
		//return apiService.bopGetItemCount(arcusRequest.getKey(), arcusRequest.getFrom(), arcusRequest.getTo(), arcusRequest.geteFlagFilter(), arcusRequest.getCompValue());
		return apiService.bopGetItemCount(arcusRequest.getKey(), arcusRequest.getFrom(), arcusRequest.getTo());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-get",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopNotBoolResponse bopGet(@RequestBody BopRequest arcusRequest) {
		if(arcusRequest.getFrom() != null) {
//			return apiService.bopGet(arcusRequest.getKey(), arcusRequest.getFrom(), arcusRequest.getTo(), arcusRequest.geteFlagFilter(), arcusRequest.getCompValue(), arcusRequest.getOffset(), arcusRequest.getCount(), arcusRequest.getWithDelete(), arcusRequest.getDropIfEmpty());
			return apiService.bopGet(arcusRequest.getKey(), arcusRequest.getFrom(), arcusRequest.getTo(), arcusRequest.getOffset(), arcusRequest.getCount(), arcusRequest.getWithDelete(), arcusRequest.getDropIfEmpty());
		}else {
//			return apiService.bopGet(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.geteFlagFilter(), arcusRequest.getCompValue(), arcusRequest.getWithDelete(), arcusRequest.getDropIfEmpty());
			return apiService.bopGet(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.getWithDelete(), arcusRequest.getDropIfEmpty());
		}
	}	
	

	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-get-bulk",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopNotBoolResponse bopGetBulk(@RequestBody BopRequest arcusRequest) {
//		return apiService.bopGetBulk(arcusRequest.getKeyList(), arcusRequest.getFrom(), arcusRequest.getTo(), arcusRequest.geteFlagFilter(), arcusRequest.getCompValue(), arcusRequest.getOffset(), arcusRequest.getCount());
		return apiService.bopGetBulk(arcusRequest.getKeyList(), arcusRequest.getFrom(), arcusRequest.getTo(), arcusRequest.getOffset(), arcusRequest.getCount());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-smget",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopNotBoolResponse bopSMGet(@RequestBody BopRequest arcusRequest) {
//		return apiService.bopSMGet(arcusRequest.getKeyList(), arcusRequest.getFrom(), arcusRequest.getTo(), arcusRequest.geteFlagFilter(), arcusRequest.getCompValue(), arcusRequest.getCount(), arcusRequest.getSMGetMode());
		return apiService.bopSMGet(arcusRequest.getKeyList(), arcusRequest.getFrom(), arcusRequest.getTo(), arcusRequest.getCount(), arcusRequest.getSMGetMode());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-find-position",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopNotBoolResponse bopFindPosition(@RequestBody BopRequest arcusRequest) {
		return apiService.bopFindPosition(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.getOrder());
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-get-by-position",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopNotBoolResponse bopGetByPosition(@RequestBody BopRequest arcusRequest) {
		if (arcusRequest.getFrom() != null) {
			return apiService.bopGetByPosition(arcusRequest.getKey(), arcusRequest.getOrder(), arcusRequest.getFrom(), arcusRequest.getTo());
		} else {
		    return apiService.bopGetByPosition(arcusRequest.getKey(), arcusRequest.getOrder(), arcusRequest.getPosition());
		}
	}
	
	@RequestMapping(value="/${arcus.apiVersion}/${arcus.serviceCode}/async-bop-find-position-with-get",method=RequestMethod.POST)
	@ResponseBody
	ArcusBopNotBoolResponse bopFindPositionWithGet(@RequestBody BopRequest arcusRequest) {
		return apiService.bopFindPositionWithGet(arcusRequest.getKey(), arcusRequest.getBkey(), arcusRequest.getOrder(), arcusRequest.getCount());
	}
}