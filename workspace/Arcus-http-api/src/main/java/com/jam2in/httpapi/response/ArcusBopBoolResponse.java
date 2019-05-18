package com.jam2in.httpapi.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.httpapi.request.ValueDeserializer;

import net.spy.memcached.collection.CollectionResponse;

public class ArcusBopBoolResponse {
	@JsonDeserialize(using = ValueDeserializer.class)
	@JsonRawValue
	private Object result;
	private String explanation;
	
	public ArcusBopBoolResponse(Object result, CollectionResponse explanation) {
		//this.result = "SUCCESS";
		if(result.equals(true)) this.result = "SUCCESS";
		else if(result.equals(false)) this.result = "FAIL";
		else this.result = result;
		
		if(explanation.equals(CollectionResponse.CREATED)) this.explanation = "생성 성공.";
		else if(explanation.equals(CollectionResponse.EXISTS)) this.explanation = "동일 key가 이미 존재합니다.";
		else if(explanation.equals(CollectionResponse.STORED)) this.explanation = "Element만 삽입.";
		else if(explanation.equals(CollectionResponse.CREATED_STORED)) this.explanation = "B+tree collection 생성하고 element를 삽입.";
		else if(explanation.equals(CollectionResponse.REPLACED)) this.explanation = "Element가 교체됨.";
		else if(explanation.equals(CollectionResponse.TRIMMED)) this.explanation = "Element가 삽입되고, 삽입으로 trimmed element가 조회됨.";
		else if(explanation.equals(CollectionResponse.UPDATED)) this.explanation = "Element가 변경됨.";
		else if(explanation.equals(CollectionResponse.NOT_FOUND)) this.explanation = "Key miss (주어진 key에 해당하는 item이 없음)";
		else if(explanation.equals(CollectionResponse.NOT_FOUND_ELEMENT)) this.explanation = "주어진 bkey를 가진 element가 없음.";
		else if(explanation.equals(CollectionResponse.TYPE_MISMATCH)) this.explanation = "해당 item이 b+tree가 아님.";
		else if(explanation.equals(CollectionResponse.BKEY_MISMATCH)) this.explanation = "주어진 bkey 유형이 기존 bkey 유형과 다름.";
		else if(explanation.equals(CollectionResponse.EFLAG_MISMATCH)) this.explanation = "주어진 eFlagUpate가 해당 element의 eflag 데이터와 불일치.";
		else if(explanation.equals(CollectionResponse.DELETED)) this.explanation = "Element 삭제.";
		else if(explanation.equals(CollectionResponse.DELETED_DROPPED)) this.explanation = "Element 삭제하고 B+tree 자체도 삭제.";
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
}