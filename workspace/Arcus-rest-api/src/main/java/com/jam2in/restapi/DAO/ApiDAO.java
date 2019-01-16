package com.jam2in.restapi.DAO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.collection.CollectionAttributes;
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
	public Future<Map<String, CollectionOperationStatus>> setBulk(List<String> keys, int expireTime, Object value){
		Future<Map<String,CollectionOperationStatus>> future = null;
		
		future = arcusClient.asyncSetBulk(keys, expireTime, value);
		
		return future;
	}
	public Future<Map<String, CollectionOperationStatus>> setBulk(Map<String, Object> map, int expireTime){
		Future<Map<String,CollectionOperationStatus>> future = null;
		
		future = arcusClient.asyncSetBulk(map, expireTime);
		
		return future;
	}
	public Future<Object> get(String key){
		Future<Object> future = null;
		
		future = arcusClient.asyncGet(key);
		
		return future;
	}
	public Future<Map<String, Object>> getBulk(Collection<String> keys){
		Future<Map<String, Object>> future = null;
		
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
	
}
