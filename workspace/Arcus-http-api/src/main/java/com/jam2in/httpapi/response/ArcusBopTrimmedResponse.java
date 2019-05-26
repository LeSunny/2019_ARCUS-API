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
	private String explanation = null;
	private String trimmed_item = null;
	public ArcusBopTrimmedResponse(boolean result, CollectionResponse explanation, Element<Object> element) {
		if(result == true) {
			this.result = "SUCCESS";
			if(explanation.equals(CollectionResponse.STORED)) this.explanation += "Element만 삽입함.";
			else if(explanation.equals(CollectionResponse.CREATED_STORED)) this.explanation += "B+tree collection 생성하고 element를 삽입함.";
			else if(explanation.equals(CollectionResponse.REPLACED)) this.explanation += "B+tree collection 생성하고 element를 삽입함.";
			else if(explanation.equals(CollectionResponse.TRIMMED)) this.explanation += "B+tree collection 생성하고 element를 삽입함.";
			trimmed_item = "The insertion was scceeded and an element "+ element + " was trimmed out.";
		}
		else if(result == false) {
			this.result = "FAIL";
			if(explanation.equals(CollectionResponse.NOT_FOUND)) this.explanation += "Key miss (주어진 key에 해당하는 item이 없음)";
			else if(explanation.equals(CollectionResponse.TYPE_MISMATCH)) this.explanation += "해당 item이 b+tree가 아님.";
			else if(explanation.equals(CollectionResponse.BKEY_MISMATCH)) this.explanation += "주어진 bkey 유형이 기존 bkey 유형과 다름.";
			else if(explanation.equals(CollectionResponse.ELEMENT_EXISTS)) this.explanation += "주어진 bkey를 가진 element가 이미 존재함.";
			else if(explanation.equals(CollectionResponse.OVERFLOWED)) this.explanation += "최대 저장가능한 개수만큼 element들이 존재함.";
			else if(explanation.equals(CollectionResponse.OUT_OF_RANGE)) this.explanation += "주어진 bkey가 b+tree trimmed 영역에 해당됨.";
		}
	}
	
	public Object getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
