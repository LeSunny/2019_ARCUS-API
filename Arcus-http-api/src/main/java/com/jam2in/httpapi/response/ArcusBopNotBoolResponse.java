package com.jam2in.httpapi.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.httpapi.request.ValueDeserializer;

import net.spy.memcached.collection.CollectionResponse;

public class ArcusBopNotBoolResponse {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	private Object result; // success or fail
	private Object response = null; // future.get 결과

	public ArcusBopNotBoolResponse(Object results, CollectionResponse explanation) {
		if(results.equals(null)) {
			this.result = "FAIL";
		}
		else {
			this.result = "SUCCESS (" + results + ")";
			response = explanation;
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
