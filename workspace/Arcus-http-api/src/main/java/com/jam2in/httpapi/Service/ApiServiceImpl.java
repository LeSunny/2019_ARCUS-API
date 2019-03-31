package com.jam2in.httpapi.Service;

import java.io.IOException;
import java.util.Collection;
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
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jam2in.httpapi.DAO.ApiDAO;
import com.jam2in.httpapi.response.ArcusLongSuccessResponse;
import com.jam2in.httpapi.response.ArcusSetBulkSuccessResponse;
import com.jam2in.httpapi.response.ArcusSuccessResponse;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.internal.CollectionFuture;
import net.spy.memcached.ops.CollectionOperationStatus;

@Service("apiService")
public class ApiServiceImpl implements ApiService {

	@Resource(name="apiDAO")
	private ApiDAO apiDAO;
	
	public ArcusSuccessResponse set(String key, int expireTime, String value) {
		
		Future<Boolean> future = null;
		boolean result = false;	
		future = apiDAO.set(key, expireTime, value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();
//		System.out.println(result);
//		
//		String jsonString = "{\"result\":\"" + "SUCCESS\"}";
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
	public ArcusSuccessResponse add(String key, int expireTime, String value) {
		
		Future<Boolean> future = null;
		boolean result = false;
		
		future = apiDAO.add(key, expireTime, value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();

//		System.out.println(result);
//		
//		String jsonString = "{\"result\":\"" + "SUCCESS\"}";
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
	public ArcusSuccessResponse replace(String key, int expireTime, String value) {
		
		Future<Boolean> future = null;
		boolean result = false;
		
		future = apiDAO.replace(key, expireTime, value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return new ArcusSuccessResponse();
//
//		System.out.println(result);
//		
//		String jsonString = "{\"result\":\"" + "SUCCESS\"}";
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
	public ArcusSuccessResponse prepend(long cas, String key, Object value) {
		
		Future<Boolean> future = null;
		boolean result = false;
		
		future = apiDAO.prepend(cas,key,value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();

//		System.out.println(result);
//		
//		String jsonString = "{\"result\":\"" + "SUCCESS\"}";
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
	public ArcusSuccessResponse append(long cas, String key, Object value) {

		Future<Boolean> future = null;
		boolean result = false;
		future = apiDAO.append(cas, key, value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();

//		System.out.println(result);
//		
//		String jsonString = "{\"result\":\"" + "SUCCESS\"}";
//
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = null;
//		try {
//			returnJson = mapper.readTree(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return returnJson;
		
	}
	public ArcusSetBulkSuccessResponse setBulk(List<String> key, int expireTime, Object value) {
		
		Future<Map<String,CollectionOperationStatus>> future = null;
		Map<String,CollectionOperationStatus> resultMap = null;
		
		future = apiDAO.setBulk(key, expireTime, value);
		try {
			resultMap = future.get(700L, TimeUnit.MILLISECONDS);
			System.out.println("result Map : "+resultMap);//.get("value")+"value");
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusSetBulkSuccessResponse response = new ArcusSetBulkSuccessResponse();
		response.setResult(resultMap);
		return response;
		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = mapper.convertValue(resultMap, JsonNode.class);
//		
//		return returnJson;
		
	}
	public ArcusSetBulkSuccessResponse setBulk(Map<String, Object> map, int expireTime) {
		
		Future<Map<String,CollectionOperationStatus>> future = null;
		Map<String,CollectionOperationStatus> resultMap = null;
		
		future = apiDAO.setBulk(map, expireTime);

		try {
			resultMap = future.get(700L, TimeUnit.MILLISECONDS);
			System.out.println("result Map : "+resultMap);//.get("value")+"value");
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusSetBulkSuccessResponse response = new ArcusSetBulkSuccessResponse();
		response.setResult(resultMap);
		return response;
		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode returnJson = mapper.convertValue(resultMap, JsonNode.class);
//		
//		return returnJson;
		
	}
	public ArcusSuccessResponse get(String key) {
		

		Future<Object> future = null;
		Object value = null;
		
		future = apiDAO.get(key);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		
		ArcusSuccessResponse response = new ArcusSuccessResponse();
		response.setResult(value);
		return response;
		
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
	public ArcusSuccessResponse getBulk(Collection<String> key) {
		
		Future<Map<String,Object>> future = null;
		Map<String,Object> resultMap = null;
		
		future = apiDAO.getBulk(key);
		
		try {
			resultMap = future.get(700L, TimeUnit.MILLISECONDS);
			System.out.println("result Map : "+resultMap);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusSuccessResponse response = new ArcusSuccessResponse();
		response.setResult(resultMap);
		return response;
		
//		//Map to json
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode resultJson = mapper.convertValue(resultMap, JsonNode.class);
//		System.out.println(resultJson);
//		return resultJson;
		
	}
	
	public ArcusSuccessResponse getAttr(String key) {
		
		CollectionFuture<CollectionAttributes> future = null;
		CollectionAttributes result = null;
		
		future = apiDAO.getAttr(key);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
			System.out.println(result);/*flags=0 expiretime=9982 type=kv*/
		} catch (InterruptedException | TimeoutException | ExecutionException e) {
			e.printStackTrace();
		}
		
		ArcusSuccessResponse response = new ArcusSuccessResponse();
		response.setResult(result);
		return response;
		
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
		
	}
	public ArcusLongSuccessResponse increase(String key, int by) {
		
		Future<Long> future = null;
		long value = 0;
		
		future = apiDAO.increase(key, by);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusLongSuccessResponse response = new ArcusLongSuccessResponse();
		response.setResult(value);
		return response;
		
//		String jsonString = "{\"value\":\""+value+"\"}";
//		 
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
	public ArcusLongSuccessResponse increase(String key, int by, long def, int exp) {
		
		Future<Long> future = null;
		long value = 0;
		
		future = apiDAO.increase(key, by, def, exp);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusLongSuccessResponse response = new ArcusLongSuccessResponse();
		response.setResult(value);
		return response;
		
//		String jsonString = "{\"value\":\"null\"}";
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
	public ArcusLongSuccessResponse decrease(String key, int by) {

		Future<Long> future = null;
		long value = 0;
		future = apiDAO.decrease(key, by);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusLongSuccessResponse response = new ArcusLongSuccessResponse();
		response.setResult(value);
		return response;

//		String jsonString = "{\"value\":\""+value+"\"}";
//		
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
	public ArcusLongSuccessResponse decrease(String key, int by, long def, int exp) {
	
		Future<Long> future = null;
		long value = 0;
		
		future = apiDAO.decrease(key, by, def, exp);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
			//System.out.println(value);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		ArcusLongSuccessResponse response = new ArcusLongSuccessResponse();
		response.setResult(value);
		return response;

//		String jsonString = "{\"value\":\""+value+"\"}";		
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
	public ArcusSuccessResponse delete(String key) {
		
		Future<Boolean> future = null;
		boolean result = false;	
		future = apiDAO.delete(key);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();
		
//		System.out.println(result);
//		
//		String jsonString = "{\"result\":\"" + "SUCCESS\"}";
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
}
