package com.jam2in.httpapi.response;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public class ArcusLongSuccessResponse {
	
	private Long result=null;
	
	public Long getResult() {
		return result;
	}
	
	public void setResult(Long result) {
		this.result = result;
	}
}