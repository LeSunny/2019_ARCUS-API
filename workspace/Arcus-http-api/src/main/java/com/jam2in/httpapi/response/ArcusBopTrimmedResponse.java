package com.jam2in.httpapi.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.httpapi.request.ValueDeserializer;

import net.spy.memcached.collection.CollectionResponse;
import net.spy.memcached.collection.Element;

public class ArcusBopTrimmedResponse {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	private String result = null;
	private Object response = null;
	private String trimmed_item = null;
	public ArcusBopTrimmedResponse(boolean result, CollectionResponse explanation, Element<Object> element) {
		if(result == true) {
			this.result = "SUCCESS("+element+")";
			this.response = explanation;
		}
		else if(result == false) {
			this.result = "FAIL";
			this.response = explanation;
		}
	}
	
	public Object getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public Object getExplanation() {
		return response;
	}

	public void setExplanation(Object explanation) {
		this.response = explanation;
	}

	public String getTrimmed_item() {
		return trimmed_item;
	}

	public void setTrimmed_item(String trimmed_item) {
		this.trimmed_item = trimmed_item;
	}
}
