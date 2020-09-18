package com.jam2in.httpapi.request;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class TwoRequest {
	//prepend, append
	/*@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	String value = null;*/
	Object value = null;
	String key = null;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}	
}
