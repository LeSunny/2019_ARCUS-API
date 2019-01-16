package com.jam2in.restapi.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public interface ApiService {

	public JsonNode set(String key, int expireTime, String value);
	public JsonNode add(String key, int expireTime, String value);
	public JsonNode replace(String key, int expireTime, String value);
	public JsonNode prepend(long cas, String key, Object value);
	public JsonNode append(long cas, String key, Object value);
	public JsonNode setBulk(List<String> key, int expireTime, Object value);
	public JsonNode setBulk(Map<String, Object> map, int expireTime);
	public JsonNode get(String key);
	public JsonNode getBulk(Collection<String> key);
	public JsonNode getAttr(String key);
	public JsonNode increase(String key, int by);
	public JsonNode increase(String key, int by, long def, int exp);
	public JsonNode decrease(String key, int by);
	public JsonNode decrease(String key, int by, long def, int exp);
	public JsonNode delete(String key);
	
}
