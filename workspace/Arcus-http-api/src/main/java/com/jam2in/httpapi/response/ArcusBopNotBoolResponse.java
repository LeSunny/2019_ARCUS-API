package com.jam2in.httpapi.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.httpapi.request.ValueDeserializer;

import net.spy.memcached.collection.CollectionResponse;

public class ArcusBopNotBoolResponse {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	private Object result; // success or fail
	private String explanation; // detail explanation
	private Object response; // future.get 결과

	public ArcusBopNotBoolResponse(Object results, CollectionResponse explanation) {
		System.out.println(results);
		if(results.equals(null)) {
			this.result = "FAIL";
			response = null;
		}
		else {
			this.result = "SUCCESS";
			response = results;
		}
		if(explanation.equals(CollectionResponse.END)) this.explanation = "정상 수행.";
		else if(explanation.equals(CollectionResponse.NOT_FOUND)) this.explanation = "Key miss (주어진 key에 해당하는 item이 없음).";
		else if(explanation.equals(CollectionResponse.NOT_FOUND_ELEMENT)) this.explanation = "주어진 bkey를 가진 element가 없음.";
		else if(explanation.equals(CollectionResponse.TYPE_MISMATCH)) this.explanation = "해당 item이 b+tree가 아님.";
		else if(explanation.equals(CollectionResponse.BKEY_MISMATCH)) this.explanation = "Element가 교체됨.";
		else if(explanation.equals(CollectionResponse.UNREADABLE)) this.explanation = "Element가 삽입되고, 삽입으로 trimmed element가 조회됨.";
		else if(explanation.equals(CollectionResponse.OVERFLOWED)) this.explanation = "Element가 변경됨.";
		else if(explanation.equals(CollectionResponse.OUT_OF_RANGE)) this.explanation = "Key miss (주어진 key에 해당하는 item이 없음)";
		else if(explanation.equals(CollectionResponse.TRIMMED)) this.explanation = "주어진 bkey를 가진 element가 없음.";
		else if(explanation.equals(CollectionResponse.DELETED)) this.explanation = "Element해당 item이 b+tree가 아님.";
		else if(explanation.equals(CollectionResponse.DELETED_DROPPED)) this.explanation = "주어진 bkey 유형이 기존 bkey 유형과 다름.";
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
