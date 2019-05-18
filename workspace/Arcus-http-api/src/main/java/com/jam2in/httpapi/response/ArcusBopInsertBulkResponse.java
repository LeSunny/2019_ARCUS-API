package com.jam2in.httpapi.response;

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
	private String explanation;
	private Object response;

	public ArcusBopInsertBulkResponse(Map<Object, CollectionOperationStatus> results, Map<Long, Object> elements) {
		if(results.isEmpty()) {
			this.result = "SUCCRESS";
			response = null;
		}
		else {
			this.result = "PARTIAL FAIL";
			response = results;
			
			for (Map.Entry<Object, CollectionOperationStatus> entry : results.entrySet() ) {
				if (entry.getKey() instanceof Integer) {
				    this.response += "Failed item=" + elements.get(entry.getKey()) + "\r\n";
				} else {
					this.response += "failed item=" + entry.getKey() + "\r\n";
				}
				if(entry.getValue().getResponse().equals(CollectionResponse.END)) this.explanation = "�젙�긽 �닔�뻾.";
				else if(entry.getValue().getResponse().equals(CollectionResponse.NOT_FOUND)) this.explanation = "Key miss (二쇱뼱吏� key�뿉 �빐�떦�븯�뒗 item�씠 �뾾�쓬).";
				else if(entry.getValue().getResponse().equals(CollectionResponse.NOT_FOUND_ELEMENT)) this.explanation = "二쇱뼱吏� bkey瑜� 媛�吏� element媛� �뾾�쓬.";
				else if(entry.getValue().getResponse().equals(CollectionResponse.TYPE_MISMATCH)) this.explanation = "�빐�떦 item�씠 b+tree媛� �븘�떂.";
				else if(entry.getValue().getResponse().equals(CollectionResponse.BKEY_MISMATCH)) this.explanation = "Element媛� 援먯껜�맖.";
				else if(entry.getValue().getResponse().equals(CollectionResponse.UNREADABLE)) this.explanation = "Element媛� �궫�엯�릺怨�, �궫�엯�쑝濡� trimmed element媛� 議고쉶�맖.";
				else if(entry.getValue().getResponse().equals(CollectionResponse.OVERFLOWED)) this.explanation = "Element媛� 蹂�寃쎈맖.";
				else if(entry.getValue().getResponse().equals(CollectionResponse.OUT_OF_RANGE)) this.explanation = "Key miss (二쇱뼱吏� key�뿉 �빐�떦�븯�뒗 item�씠 �뾾�쓬)";
				else if(entry.getValue().getResponse().equals(CollectionResponse.TRIMMED)) this.explanation = "二쇱뼱吏� bkey瑜� 媛�吏� element媛� �뾾�쓬.";
				else if(entry.getValue().getResponse().equals(CollectionResponse.DELETED)) this.explanation = "Element�빐�떦 item�씠 b+tree媛� �븘�떂.";
				else if(entry.getValue().getResponse().equals(CollectionResponse.DELETED_DROPPED)) this.explanation = "二쇱뼱吏� bkey �쑀�삎�씠 湲곗〈 bkey �쑀�삎怨� �떎由�.";
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
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
}
