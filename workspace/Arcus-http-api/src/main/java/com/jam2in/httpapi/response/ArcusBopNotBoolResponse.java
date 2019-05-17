package com.jam2in.httpapi.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.httpapi.request.ValueDeserializer;

import net.spy.memcached.collection.CollectionResponse;

public class ArcusBopNotBoolResponse {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	private Object result;
	private String explanation;
	private Object response;

	public ArcusBopNotBoolResponse(Object results, CollectionResponse explanation) {
		if(results.equals(null)) {
			this.result = "FAIL";
			response = null;
		}
		else {
			this.result = "SUCCESS";
			response = results;
		}
		if(response.equals(CollectionResponse.END)) this.response = "정상 수행.";
		else if(response.equals(CollectionResponse.NOT_FOUND)) this.response = "Key miss (주어진 key에 해당하는 item이 없음).";
		else if(response.equals(CollectionResponse.NOT_FOUND_ELEMENT)) this.response = "주어진 bkey를 가진 element가 없음.";
		else if(response.equals(CollectionResponse.TYPE_MISMATCH)) this.response = "해당 item이 b+tree가 아님.";
		else if(response.equals(CollectionResponse.BKEY_MISMATCH)) this.response = "Element가 교체됨.";
		else if(response.equals(CollectionResponse.UNREADABLE)) this.response = "Element가 삽입되고, 삽입으로 trimmed element가 조회됨.";
		else if(response.equals(CollectionResponse.OVERFLOWED)) this.response = "Element가 변경됨.";
		else if(response.equals(CollectionResponse.OUT_OF_RANGE)) this.response = "Key miss (주어진 key에 해당하는 item이 없음)";
		else if(response.equals(CollectionResponse.TRIMMED)) this.response = "주어진 bkey를 가진 element가 없음.";
		else if(response.equals(CollectionResponse.DELETED)) this.response = "Element해당 item이 b+tree가 아님.";
		else if(response.equals(CollectionResponse.DELETED_DROPPED)) this.response = "주어진 bkey 유형이 기존 bkey 유형과 다름.";
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
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
}
