package com.jam2in.httpapi.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;
import com.jam2in.httpapi.response.ArcusBopBoolResponse;
import com.jam2in.httpapi.response.ArcusBopNotBoolResponse;
import com.jam2in.httpapi.response.ArcusLongSuccessResponse;
import com.jam2in.httpapi.response.ArcusSetBulkSuccessResponse;
import com.jam2in.httpapi.response.ArcusSuccessResponse;

import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.collection.ElementFlagFilter;
import net.spy.memcached.collection.ElementFlagUpdate;
import net.spy.memcached.collection.ElementValueType;

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
	
	
	public ArcusBopBoolResponse bopCreate(String key, CollectionAttributes collectionAttributes);
	public ArcusBopBoolResponse bopInsert(String key, Object bkey, byte[] eFlag, Object value, CollectionAttributes attributesForCreate);
//	public ArcusBopBoolResponse bopInsertAndGetTrimmed(String key, Object bkey, byte[] eFlag, Object value, CollectionAttributes attributes);
	public ArcusBopBoolResponse bopUpsert(String key, Object bkey, byte[] eFlag, Object value, CollectionAttributes attributesForCreate);
	public ArcusBopBoolResponse bopUpdate(String key, Object bkey, ElementFlagUpdate eFlagUpdate, Object value);
	public ArcusBopBoolResponse bopDelete(String key, Object from, Object to, Integer count, Boolean dropIfEmpty);
	public ArcusBopBoolResponse bopDelete(String key, Object bkey, Boolean dropIfEmpty);
	public ArcusBopNotBoolResponse bopIncr(String key, Object subkey, Integer by, Long initial, byte[] eFlag);
	public ArcusBopNotBoolResponse bopIncr(String key, Object bkey, Integer by);
	public ArcusBopNotBoolResponse bopDecr(String key, Object bkey, Integer by);
	public ArcusBopNotBoolResponse bopDecr(String key, Object subkey, Integer by, Long initial, byte[] eFlag);
	public ArcusBopNotBoolResponse bopGetItemCount(String key, Object from, Object to);
	public ArcusBopNotBoolResponse bopGet(String key, Object from, Object to, Integer offset, Integer count,
			Boolean withDelete, Boolean dropIfEmpty);
	public ArcusBopNotBoolResponse bopGet(String key, Object bkey, Boolean withDelete, Boolean dropIfEmpty);
	
	
}
