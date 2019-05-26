package com.jam2in.httpapi.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;
import com.jam2in.httpapi.response.ArcusBopBoolResponse;
import com.jam2in.httpapi.response.ArcusBopInsertBulkResponse;
import com.jam2in.httpapi.response.ArcusBopNotBoolResponse;
import com.jam2in.httpapi.response.ArcusLongSuccessResponse;
import com.jam2in.httpapi.response.ArcusSetBulkSuccessResponse;
import com.jam2in.httpapi.response.ArcusSuccessResponse;

import net.spy.memcached.collection.BTreeOrder;
import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.collection.Element;
import net.spy.memcached.collection.ElementFlagFilter;
import net.spy.memcached.collection.ElementFlagUpdate;
import net.spy.memcached.collection.ElementValueType;
import net.spy.memcached.collection.SMGetMode;
import net.spy.memcached.collection.ElementFlagFilter.BitWiseOperands;

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
	public ArcusBopBoolResponse bopUpdate(String key, Object bkey, String eFlagUpdate, Object flag, BitWiseOperands bitOp, Integer eFlagOffset, Object value);
	public ArcusBopBoolResponse bopDelete(String key, Object from, Object to, String eFlagFilter, String compValue, Integer count, Boolean dropIfEmpty);
	public ArcusBopBoolResponse bopDelete(String key, Object bkey, String eFlagFilter, String compValue, Boolean dropIfEmpty);
	public ArcusBopNotBoolResponse bopIncr(String key, Object subkey, Integer by, Long initial, byte[] eFlag);
	public ArcusBopNotBoolResponse bopIncr(String key, Object bkey, Integer by);
	public ArcusBopNotBoolResponse bopDecr(String key, Object bkey, Integer by);
	public ArcusBopNotBoolResponse bopDecr(String key, Object subkey, Integer by, Long initial, byte[] eFlag);
	public ArcusBopNotBoolResponse bopGetItemCount(String key, Object from, Object to, String eFlagFilter, String compValue);
	public ArcusBopNotBoolResponse bopGet(String key, Object from, Object to, String eFlagFilter, String compValue, Integer offset, Integer count,
			Boolean withDelete, Boolean dropIfEmpty);
	public ArcusBopNotBoolResponse bopGet(String key, Object bkey, String eFlagFilter, String compValue, Boolean withDelete, Boolean dropIfEmpty);
	
	//일괄삽입 수정
	public ArcusBopInsertBulkResponse bopPipedInsertBulk(String key, Map<Long, Object> elementsWithMap, CollectionAttributes attributesForCreate);
	public ArcusBopInsertBulkResponse bopPipedInsertBulk(String key, List<Element<Object>> elementsWithList, CollectionAttributes attributesForCreate);
	public ArcusBopInsertBulkResponse bopInsertBulk(List<String> keyList, Object bkey, byte[] eflag, Object value, CollectionAttributes attributesForCreate);
	//일괄변경 추가
	public ArcusBopNotBoolResponse bopGetBulk(List<String> keyList, Object from, Object to, String eFlagFilter, String compValue, Integer offset, Integer count);
	public ArcusBopNotBoolResponse bopSMGet(List<String> keyList, Object from, Object to, String eFlagFilter, String compValue, Integer count, SMGetMode smgetMode);
	public ArcusBopNotBoolResponse bopFindPosition(String key, Object bkey, BTreeOrder order);
	public ArcusBopNotBoolResponse bopGetByPosition(String key, BTreeOrder order, Integer position);
	public ArcusBopNotBoolResponse bopGetByPosition(String key, BTreeOrder order, Object from, Object to);
	public ArcusBopNotBoolResponse bopFindPositionWithGet(String key, Object bkey, BTreeOrder order, Integer count);
	
}