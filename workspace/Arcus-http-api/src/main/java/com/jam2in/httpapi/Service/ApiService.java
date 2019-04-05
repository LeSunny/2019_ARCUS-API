package com.jam2in.httpapi.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;
import com.jam2in.httpapi.response.ArcusLongSuccessResponse;
import com.jam2in.httpapi.response.ArcusSetBulkSuccessResponse;
import com.jam2in.httpapi.response.ArcusSuccessResponse;

public interface ApiService {

	public ArcusSuccessResponse set(String key, int expireTime, Object value);
	public ArcusSuccessResponse add(String key, int expireTime, Object value);
	public ArcusSuccessResponse replace(String key, int expireTime, Object value);
	public ArcusSuccessResponse prepend(long cas, String key, Object value);
	public ArcusSuccessResponse append(long cas, String key, Object value);
	
	public ArcusSetBulkSuccessResponse setBulk(List<String> key, int expireTime, Object value);
	public ArcusSetBulkSuccessResponse setBulk(Map<String, Object> map, int expireTime);
	
	public ArcusSuccessResponse get(String key);
	public ArcusSuccessResponse getBulk(Collection<String> key);
	public ArcusSuccessResponse getAttr(String key);
	public ArcusLongSuccessResponse increase(String key, int by);
	public ArcusLongSuccessResponse increase(String key, int by, long def, int exp);
	public ArcusLongSuccessResponse decrease(String key, int by);
	public ArcusLongSuccessResponse decrease(String key, int by, long def, int exp);
	public ArcusSuccessResponse delete(String key);
	
}
