package com.jam2in.httpapi.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
public class ThreeSingularRequest {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	String value = null;
	
	String key = null;
	int expireTime;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	
	
	
}
