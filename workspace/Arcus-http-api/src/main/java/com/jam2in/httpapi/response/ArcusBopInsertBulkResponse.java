package com.jam2in.httpapi.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.httpapi.request.ValueDeserializer;

import net.spy.memcached.collection.CollectionResponse;
import net.spy.memcached.ops.CollectionOperationStatus;

public class ArcusBopInsertBulkResponse {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	private Object result;
	private String explanation = "";
	//private Object response;

	public ArcusBopInsertBulkResponse(Map<Object, CollectionOperationStatus> results, Map<Long, Object> elements) {

		if(results.isEmpty()) {
			this.result = "SUCCRESS";
			//response = null;
			explanation = "생성 성공";
		}
		else {
			this.result = "PARTIAL FAIL";
			//response = results;

	         List<Long> bkeyList = new ArrayList<Long>();
	         for(Map.Entry<Long, Object> entry : elements.entrySet()) {
	             bkeyList.add(entry.getKey());
	         }

	         for(Map.Entry<Object, CollectionOperationStatus> entry : results.entrySet()) {
				if(entry.getValue().getResponse().equals(CollectionResponse.ELEMENT_EXISTS))explanation += "Failed item=" + bkeyList.get((int) entry.getKey()) +":Element가 이미 존재함. ";			
				else explanation += "Failed item=" + bkeyList.get((int) entry.getKey()) +":"+ entry.getValue().getResponse() + " ";			
			}
		}
	}	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
//	public Object getResponse() {
//		return response;
//	}
//	public void setResponse(Object response) {
//		this.response = response;
//	}
}
