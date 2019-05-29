package com.jam2in.httpapi.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.httpapi.request.ValueDeserializer;

import net.spy.memcached.collection.CollectionResponse;

public class ArcusBopBoolResponse {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	private Object result;
	private Object response;
	
	public ArcusBopBoolResponse(Object result, CollectionResponse explanation) {
		if(result.equals(true)) this.result = "SUCCESS";
		else this.result = "FAIL";
		response = explanation;
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