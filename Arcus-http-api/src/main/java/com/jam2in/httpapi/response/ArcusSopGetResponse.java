package com.jam2in.httpapi.response;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.httpapi.request.ValueDeserializer;

import net.spy.memcached.collection.CollectionResponse;

public class ArcusSopGetResponse {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	private Object result;
	private Object response = null;

	public ArcusSopGetResponse(Set<Object> results, CollectionResponse response) {
		if(results.equals(null)) {
			this.result = "FAIL";
		}
		else {
			this.result = "SUCCESS (" + results + ")";
			this.response = response;
		}
	}	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
}
