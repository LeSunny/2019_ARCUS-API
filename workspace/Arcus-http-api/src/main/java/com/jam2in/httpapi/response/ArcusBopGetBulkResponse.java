package com.jam2in.httpapi.response;

import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jam2in.httpapi.request.ValueDeserializer;

import net.spy.memcached.collection.BTreeGetResult;
import net.spy.memcached.collection.ByteArrayBKey;

public class ArcusBopGetBulkResponse {
  @JsonDeserialize(using = ValueDeserializer.class)
  @JsonRawValue
  private Object result;
  private Object response = "";
  private String explanation;
//class 두개 만들기..
  public ArcusBopGetBulkResponse(Map<String, BTreeGetResult<Object, Object>> results) {

	    if(results.equals(null)) {
	      this.result = "FAIL";
	    }
	    else {
	      this.result = results;
	      this.response = "조회 성공\n";

	      StringBuilder buildExplanation = new StringBuilder();
	      for (Entry<String, BTreeGetResult<Object, Object>> entry : results.entrySet()) {
	        buildExplanation.append("{ key=");
	        buildExplanation.append(entry.getKey());
	        buildExplanation.append(" , result code=");
	        buildExplanation.append(entry.getValue().getCollectionResponse().getMessage());
	        buildExplanation.append(" }\n");
	      }
	      this.explanation = buildExplanation.toString();
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

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
}