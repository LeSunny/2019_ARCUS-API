package com.jam2in.restapi.request;

public class FourRequest {
	//incr(), decr()
	
	Integer by = null;
	Long def = null;
	String key = null;
	int expireTime;
	
	public Integer getBy() {
		return by;
	}
	public void setBy(Integer by) {
		this.by = by;
	}
	public Long getDef() {
		return def;
	}
	public void setDef(Long def) {
		this.def = def;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}
	
}
