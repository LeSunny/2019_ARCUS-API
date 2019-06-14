package com.jam2in.httpapi.request;

import net.spy.memcached.collection.*;

public class SopRequest {
	String key = null;ElementValueType valueType = null;
	
	CollectionAttributes attributes = null;
	CollectionAttributes attributesForCreate = null;
	Object value = null;
	
	int count;
	boolean withDelete;
	boolean dropIfEmpty;


	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public ElementValueType getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		if(valueType.equals("STRING")) {
			this.valueType = ElementValueType.STRING;
		}
		else if(valueType.contentEquals("OTHERS")) {
			this.valueType = ElementValueType.OTHERS;
		}
		else if(valueType.equals("LONG")) {
			this.valueType = ElementValueType.LONG;
		}
		else if(valueType.equals("INTEGER")) {
			this.valueType = ElementValueType.INTEGER;
		}
		else if(valueType.equals("BOOLEAN")) {
			this.valueType = ElementValueType.BOOLEAN;
		}
		else if(valueType.equals("DATE")) {
			this.valueType = ElementValueType.DATE;
		}
		else if(valueType.equals("BYTE")) {
			this.valueType = ElementValueType.BYTE;
		}
		else if(valueType.equals("FLOAT")) {
			this.valueType = ElementValueType.FLOAT;
		}
		else if(valueType.equals("DOUBLE")) {
			this.valueType = ElementValueType.DOUBLE;
		}
		else if(valueType.equals("BYTEARRAY")) {
			this.valueType = ElementValueType.BYTEARRAY;
		}
	}
	public CollectionAttributes getAttributes() {
		return attributes;
	}
	public void setAttributes(CollectionAttributes attributes) {
		this.attributes = attributes;
	}
	public CollectionAttributes getAttributesForCreate() {
		return attributesForCreate;
	}
	public void setAttributesForCreate(CollectionAttributes attributesForCreate) {
		this.attributesForCreate = attributesForCreate;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean getWithDelete() {
		return withDelete;
	}
	public void setWithDelete(boolean withDelete) {
		this.withDelete = withDelete;
	}
	public boolean getDropIfEmpty() {
		return dropIfEmpty;
	}
	public void setDropIfEmpty(boolean dropIfEmpty) {
		this.dropIfEmpty = dropIfEmpty;
	}
	
}
