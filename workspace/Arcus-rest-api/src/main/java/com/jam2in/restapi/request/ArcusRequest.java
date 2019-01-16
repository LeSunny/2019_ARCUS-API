package com.jam2in.restapi.request;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public class ArcusRequest {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	String value = null;
	List<String> values = null;
	
	String key = null;
	Collection<String> keys = null;
	int expireTime;
	
	
	Integer by = null;
	Long def = null;
		
	public Integer getBy() {
		return by;
	}
	public void setBy(Integer by) {
		this.by = by;
	}
	
	public Long getDef() {
		return def;
	}
	public void setDef(Long def) {
		this.def = def;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public int getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}
	
	public String getValue() {		
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
	
	public Collection<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
}