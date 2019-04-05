package com.jam2in.httpapi.response;

import java.util.Map;

import net.spy.memcached.ops.CollectionOperationStatus;

public class ArcusSetBulkSuccessResponse {

	private Map<String,CollectionOperationStatus> result = null;
	
	public Map<String,CollectionOperationStatus> getResult() {
		return result;
	}
	
	public void setResult(Map<String,CollectionOperationStatus> result) {
		//System.out.println("result="+result);
		this.result = result;
	}
}