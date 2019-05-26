package com.jam2in.httpapi.DAO;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.collection.*;
import net.spy.memcached.internal.CollectionFuture;
import net.spy.memcached.internal.CollectionGetBulkFuture;
import net.spy.memcached.internal.SMGetFuture;
import net.spy.memcached.ops.CollectionOperationStatus;

@Repository("apiDAO")
public class ApiDAO {

	@Autowired
	private ArcusClient arcusClient;

	public Future<Boolean> set(String key, int expireTime, String value) {
		
		Future<Boolean> future = null;
		future = arcusClient.set(key, expireTime, value);
		return future;
		
	}
	public Future<Boolean> add(String key, int expireTime, String value){
		
		Future<Boolean> future = null;
		future = arcusClient.add(key, expireTime, value);
		return future;
		
	}
	public Future<Boolean> replace(String key, int expireTime, String value){

		Future<Boolean> future = null;
		future = arcusClient.replace(key, expireTime, value);
		return future;
		
	}
	
	public Future<Boolean> prepend(long cas, String key, Object value) {
		
		Future<Boolean> future = null;
		future = arcusClient.prepend(cas, key, value);
		return future;
		
	}
	public Future<Boolean> append(long cas, String key, Object value){
		
		Future<Boolean> future = null;
		future = arcusClient.append(cas, key, value);
		return future;
		
	}
	public Future<Map<String,CollectionOperationStatus>> setBulk(List<String> keys, int expireTime, Object value){
	
		Future<Map<String,CollectionOperationStatus>> future = null;
		future = arcusClient.asyncSetBulk(keys, expireTime, value);
		return future;
		
	}
	public Future<Map<String,CollectionOperationStatus>> setBulk(Map<String, Object> map, int expireTime){
	
		Future<Map<String,CollectionOperationStatus>> future = null;
		future = arcusClient.asyncSetBulk(map, expireTime);
		return future;

	}
	public Future<Object> get(String key){
		
		Future<Object> future = null;
		future = arcusClient.asyncGet(key);
		return future;
		
	}
	public Future<Map<String,Object>> getBulk(Collection<String> keys){
	
		Future<Map<String,Object>> future = null;
		future = arcusClient.asyncGetBulk(keys);
		return future;
	
	}
	public CollectionFuture<CollectionAttributes> getAttr(String key){
		
		CollectionFuture<CollectionAttributes> future = null;
		future = arcusClient.asyncGetAttr(key);
		return future;
		
	}
	public Future<Long> increase(String key, int by){

		Future<Long> future = null;
		future = arcusClient.asyncIncr(key, by);
		return future;
	
	}
	public Future<Long> increase(String key, int by, long def, int expireTime){
		
		Future<Long> future = null;
		future = arcusClient.asyncIncr(key, by, def, expireTime);
		return future;
	
	}
	public Future<Long> decrease(String key, int by){
		
		Future<Long> future = null;
		future = arcusClient.asyncDecr(key, by);
		return future;
		
	}
	public Future<Long> decrease(String key, int by, long def, int expireTime){
	
		Future<Long> future = null;
		future = arcusClient.asyncDecr(key, by, def, expireTime);
		return future;
	
	}
	public Future<Boolean> delete(String key){

		Future<Boolean> future = null;
		future = arcusClient.delete(key);
		return future;
		
	}
	public CollectionFuture<Boolean> bopCreate(String key, ElementValueType valueType, CollectionAttributes attributes) {
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopCreate(key, valueType, attributes);
		return future;
	}
	public CollectionFuture<Boolean> bopInsert(String key, long bkey, byte[] eFlag, Object value,
			CollectionAttributes attributesForCreate) {
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopInsert(key, bkey, eFlag, value, attributesForCreate);
		return future;
	}
	public CollectionFuture<Boolean> bopInsert(String key, byte[] bkey, byte[] eFlag, Object value,
			CollectionAttributes attributesForCreate) {
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopInsert(key, bkey, eFlag, value, attributesForCreate);
		return future;
	}
	public CollectionFuture<Map<Integer, CollectionOperationStatus>> bopPipedInsertBulk(String key, Map<Long, Object> elementsWithMap, CollectionAttributes attributesForCreate){
		CollectionFuture<Map<Integer, CollectionOperationStatus>> future = null;
		future = arcusClient.asyncBopPipedInsertBulk(key, elementsWithMap, attributesForCreate);
		return future;
	}
	
	//일괄삽입추가
	public CollectionFuture<Map<Integer, CollectionOperationStatus>>asyncBopPipedInsertBulk(String key, List<Element<Object>> elements, CollectionAttributes attributesForCreate){
		CollectionFuture<Map<Integer, CollectionOperationStatus>> future = null;
		future = arcusClient.asyncBopPipedInsertBulk(key, elements, attributesForCreate);
		return future;
		
	}	

	public Future<Map<String, CollectionOperationStatus>> bopInsertBulk(List<String> keyList, long bkey, byte[] eflag, Object value, CollectionAttributes attributesForCreate){
		Future<Map<String, CollectionOperationStatus>> future = null;
		future = arcusClient.asyncBopInsertBulk(keyList, bkey, eflag, value, attributesForCreate);
		return future;
	}
	
	public Future<Map<String, CollectionOperationStatus>> bopInsertBulk(List<String> keyList, byte[] bkey, byte[] eflag, Object value, CollectionAttributes attributesForCreate){
		Future<Map<String, CollectionOperationStatus>> future = null;
		future = arcusClient.asyncBopInsertBulk(keyList, bkey, eflag, value, attributesForCreate);
		return future;
	}
	
	//일괄변경 추가
	CollectionFuture<Map<Integer, CollectionOperationStatus>> asyncBopPipedUpdateBulk(String key, List<Element<Object>> elements){
		CollectionFuture<Map<Integer, CollectionOperationStatus>> future = null;
		future = arcusClient.asyncBopPipedUpdateBulk(key, elements);
		return future;
	}
	
	public CollectionFuture<Boolean> bopUpsert(String key, long bkey, byte[] eFlag, Object value, CollectionAttributes attributesForCreate){
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopUpsert(key, bkey, eFlag, value, attributesForCreate);
		return future;
	}
	public CollectionFuture<Boolean> bopUpsert(String key, byte[] bkey, byte[] eFlag, Object value, CollectionAttributes attributesForCreate){
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopUpsert(key, bkey, eFlag, value, attributesForCreate);
		return future;
	}
	public CollectionFuture<Boolean> bopUpdate(String key, long bkey, ElementFlagUpdate eFlagUpdate, Object value){
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopUpdate(key, bkey, eFlagUpdate, value);
		return future;
	}
	public CollectionFuture<Boolean> bopUpdate(String key, byte[] bkey, ElementFlagUpdate eFlagUpdate, Object value){
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopUpdate(key, bkey, eFlagUpdate, value);
		return future;
	}
	public CollectionFuture<Boolean> bopDelete(String key, long bkey, ElementFlagFilter eFlagFilter, boolean dropIfEmpty){
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopDelete(key, bkey, eFlagFilter, dropIfEmpty);
		return future;
	}
	public CollectionFuture<Boolean> bopDelete(String key, byte[] bkey, ElementFlagFilter eFlagFilter, boolean dropIfEmpty){
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopDelete(key, bkey, eFlagFilter, dropIfEmpty);
		return future;
	}
	public CollectionFuture<Boolean> bopDelete(String key, long from, long to, ElementFlagFilter eFlagFilter, int count, boolean dropIfEmpty){
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopDelete(key, from, to, eFlagFilter, count, dropIfEmpty);
		return future;
	}
	public CollectionFuture<Boolean> bopDelete(String key, byte[] from, byte[] to, ElementFlagFilter eFlagFilter, int count, boolean dropIfEmpty){
		CollectionFuture<Boolean> future = null;
		future = arcusClient.asyncBopDelete(key, from, to, eFlagFilter, count, dropIfEmpty);
		return future;
	}
	public CollectionFuture<Long> bopIncr(String key, long bkey, int by){
		CollectionFuture<Long> future = null;
		future = arcusClient.asyncBopIncr(key,bkey,by);
		return future;
	}
	public CollectionFuture<Long> bopIncr(String key, byte[] bkey, int by){
		CollectionFuture<Long> future = null;
		future = arcusClient.asyncBopIncr(key,bkey,by);
		return future;
	}
	public CollectionFuture<Long> bopIncr(String key, long subkey, int by, long initial, byte[] eFlag){
		CollectionFuture<Long> future = null;
		future = arcusClient.asyncBopIncr(key, subkey, by, initial, eFlag);
		return future;
	}
	public CollectionFuture<Long> bopIncr(String key, byte[] subkey, int by, long initial, byte[] eFlag){
		CollectionFuture<Long> future = null;
		future = arcusClient.asyncBopIncr(key, subkey, by, initial, eFlag);
		return future;
	}
	public CollectionFuture<Long> bopDecr(String key, long bkey, int by){
		CollectionFuture<Long> future = null;
		future = arcusClient.asyncBopDecr(key,bkey,by);
		return future;
	}
	public CollectionFuture<Long> bopDecr(String key, byte[] bkey, int by){
		CollectionFuture<Long> future = null;
		future = arcusClient.asyncBopDecr(key,bkey,by);
		return future;
	}
	public CollectionFuture<Long> bopDecr(String key, long subkey, int by, long initial, byte[] eFlag){
		CollectionFuture<Long> future = null;
		future = arcusClient.asyncBopDecr(key, subkey, by, initial, eFlag);
		return future;
	}
	public CollectionFuture<Long> bopDecr(String key, byte[] subkey, int by, long initial, byte[] eFlag){
		CollectionFuture<Long> future = null;
		future = arcusClient.asyncBopDecr(key, subkey, by, initial, eFlag);
		return future;
	}
	public CollectionFuture<Integer> bopGetItemCount(String key, long from, long to, ElementFlagFilter eFlagFilter){
		CollectionFuture<Integer> future = arcusClient.asyncBopGetItemCount(key, from, to, eFlagFilter);
		return future;
	}
	public CollectionFuture<Integer> bopGetItemCount(String key, byte[] from, byte[] to, ElementFlagFilter eFlagFilter){
		CollectionFuture<Integer> future = arcusClient.asyncBopGetItemCount(key, from, to, eFlagFilter);
		return future;
	}
	
	public CollectionFuture<Map<Long, Element<Object>>> bopGet(String key, long bkey, ElementFlagFilter eFlagFilter, boolean withDelete, Boolean dropIfEmpty){
		CollectionFuture<Map<Long, Element<Object>>> future = arcusClient.asyncBopGet(key, bkey, eFlagFilter, withDelete, dropIfEmpty);
		return future;
	}
	public CollectionFuture<Map<ByteArrayBKey, Element<Object>>> bopGet(String key, byte[] bkey, ElementFlagFilter eFlagFilter, boolean withDelete, Boolean dropIfEmpty){
		CollectionFuture<Map<ByteArrayBKey, Element<Object>>> future = arcusClient.asyncBopGet(key, bkey, eFlagFilter, withDelete, dropIfEmpty);
		return future;
	}
	public CollectionFuture<Map<Long, Element<Object>>> bopGet(String key, long from, long to, ElementFlagFilter eFlagFilter, int offset, int count, boolean withDelete, boolean dropIfEmpty){
		CollectionFuture<Map<Long, Element<Object>>> future = arcusClient.asyncBopGet(key, from, to, eFlagFilter, offset, count, withDelete, dropIfEmpty);
		return future;
	}
	public CollectionFuture<Map<ByteArrayBKey, Element<Object>>> bopGet(String key, byte[] from, byte[] to, ElementFlagFilter eFlagFilter, int offset, int count, boolean withDelete, boolean dropIfEmpty){
		CollectionFuture<Map<ByteArrayBKey, Element<Object>>> future = arcusClient.asyncBopGet(key, from, to, eFlagFilter, offset, count, withDelete, dropIfEmpty);
		return future;
	}	
	public CollectionGetBulkFuture<Map<String, BTreeGetResult<Long, Object>>> bopGetBulk(List<String> keyList, long from, long to, ElementFlagFilter eFlagFilter, int offset, int count){
		CollectionGetBulkFuture<Map<String, BTreeGetResult<Long, Object>>> future = arcusClient.asyncBopGetBulk(keyList, from, to, eFlagFilter, offset, count);
		return future;
	}
	public CollectionGetBulkFuture<Map<String, BTreeGetResult<ByteArrayBKey, Object>>> bopGetBulk(List<String> keyList, byte[] from, byte[] to, ElementFlagFilter eFlagFilter, int offset, int count){
		CollectionGetBulkFuture<Map<String, BTreeGetResult<ByteArrayBKey, Object>>> future = arcusClient.asyncBopGetBulk(keyList, from, to, eFlagFilter, offset, count);
		return future;
	}
	public SMGetFuture<List<SMGetElement<Object>>> bopSMGet(List<String> keyList, long from, long to, ElementFlagFilter eFlagFilter, int count, SMGetMode smgetMode){
		SMGetFuture<List<SMGetElement<Object>>> future = arcusClient.asyncBopSortMergeGet(keyList, from, to, eFlagFilter, count, smgetMode);
		return future;
	}
	public SMGetFuture<List<SMGetElement<Object>>> bopSMGet(List<String> keyList, byte[] from, byte[] to, ElementFlagFilter eFlagFilter, int count, SMGetMode smgetMode){
		SMGetFuture<List<SMGetElement<Object>>> future = arcusClient.asyncBopSortMergeGet(keyList, from, to, eFlagFilter, count, smgetMode);
		return future;
	}
	public CollectionFuture<Integer> bopFindPosition(String key, long bkey, BTreeOrder order){
		CollectionFuture<Integer> future = arcusClient.asyncBopFindPosition(key, bkey, order);
		return future;
	}
	public CollectionFuture<Integer> bopFindPosition(String key, byte[] bkey, BTreeOrder order){
		CollectionFuture<Integer> future = arcusClient.asyncBopFindPosition(key, bkey, order);
		return future;
	}
	public CollectionFuture<Map<Integer, Element<Object>>> bopGetByPosition(String key, BTreeOrder order, int pos){
		CollectionFuture<Map<Integer, Element<Object>>> future = arcusClient.asyncBopGetByPosition(key, order, pos);
		return future;
	}
	public CollectionFuture<Map<Integer, Element<Object>>> bopGetByPosition(String key, BTreeOrder order, int from, int to){
		CollectionFuture<Map<Integer, Element<Object>>> future = arcusClient.asyncBopGetByPosition(key, order, from, to);
		return future;
	}
	public CollectionFuture<Map<Integer, Element<Object>>> bopFindPositionWithGet(String key, long bkey, BTreeOrder order, int count){
		CollectionFuture<Map<Integer, Element<Object>>> future = arcusClient.asyncBopFindPositionWithGet(key, bkey, order, count);
		return future;
	}
	public CollectionFuture<Map<Integer, Element<Object>>> bopFindPositionWithGet(String key, byte[] bkey, BTreeOrder order, int count){
		CollectionFuture<Map<Integer, Element<Object>>> future = arcusClient.asyncBopFindPositionWithGet(key, bkey, order, count);
		return future;
	}

}
