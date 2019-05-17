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
}
