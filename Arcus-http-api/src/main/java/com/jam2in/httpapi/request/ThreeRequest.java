package com.jam2in.httpapi.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
public class ThreeRequest {
	/*@JsonDeserialize(using = ListDeserializer.class)
	@JsonRawValue
	List<String> value= null;
	*/
	Object value = null;
	
	List<String> key = null;
	int expireTime;
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

	
	public List<String> getKey() {
		return key;
	}
	public void setKey(List<String> key) {
		this.key = key;
	}
	public int getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}	
}
