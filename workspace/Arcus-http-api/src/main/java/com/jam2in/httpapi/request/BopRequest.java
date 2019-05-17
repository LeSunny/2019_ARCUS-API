package com.jam2in.httpapi.request;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

import net.spy.memcached.collection.*;

public class BopRequest {
	String key = null;
	//ElementValueType valueType = null;
	
	CollectionAttributes attributes = null;
	CollectionAttributes attributesForCreate = null;
	Object bkey = null;
	byte[] eFlag = null;
	Object value = null;
	ElementFlagUpdate eFlagUpdate = null;
	//ElementFlagFilter eFlagFilter = null;
	Boolean dropIfEmpty = null;
	Object from = null;
	Object to = null;
	Integer count = null; // item attribute & parameter
	Integer by = null;
	Integer offset = null;
	Object subkey = null;
	Long initial = null;
	Boolean withDelete = null;
	
	public String getKey() {
		return key;
	}

	/*
	 * public ElementValueType getValueType() { return valueType; }
	 */
	public CollectionAttributes getAttributesForCreate() {
		return attributesForCreate;
	}

	public void setAttributesForCreate(CollectionAttributes attributesForCreate) {
		this.attributesForCreate = attributesForCreate;
	}

	public CollectionAttributes getAttributes() {
		return attributes;
	}
	public Object getBkey() {
		return bkey;
	}
	public byte[] geteFlag() {
		return eFlag;
	}
	public Object getValue() {
		return value;
	}
	public ElementFlagUpdate geteFlagUpdate() {
		return eFlagUpdate;
	}
//	public ElementFlagFilter geteFlagFilter() {
//		return eFlagFilter;
//	}
	public Boolean getDropIfEmpty() {
		return dropIfEmpty;
	}
	public Object getFrom() {
		return from;
	}
	public Object getTo() {
		return to;
	}
	public Integer getCount() {
		return count;
	}
	public Integer getBy() {
		return by;
	}
	public Object getSubkey() {
		return subkey;
	}
	public Long getInitial() {
		return initial;
	}
	public Boolean getWithDelete() {
		return withDelete;
	}
	public void setKey(String key) {
		this.key = key;
	}

	/*
	 * public void setValueType(ElementValueType valueType) { this.valueType =
	 * valueType; }
	 */
	public void setAttributes(CollectionAttributes attributes) {
		this.attributes = attributes;
	}
	public void setBkey(Object bkey) {
		this.bkey = bkey;
	}
	public void seteFlag(byte[] eFlag) {
		this.eFlag = eFlag;//Arrays.toString(eFlag);//(byte[]) eFlag;
		System.out.println("BopRequest : eFlag : "+Arrays.toString(this.eFlag));
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public void seteFlagUpdate(ElementFlagUpdate eFlagUpdate) {
		this.eFlagUpdate = eFlagUpdate;
	}
//	public void seteFlagFilter(ElementFlagFilter eFlagFilter) {
//		this.eFlagFilter = eFlagFilter;
//	}
	public void setDropIfEmpty(Boolean dropIfEmpty) {
		this.dropIfEmpty = dropIfEmpty;
	}
	public void setFrom(Object from) {
		this.from = from;
	}
	public void setTo(Object to) {
		this.to = to;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setBy(Integer by) {
		this.by = by;
	}
	public void setSubkey(Object subkey) {
		this.subkey = subkey;
	}
	public void setInitial(Long initial) {
		this.initial = initial;
	}
	public void setWithDelete(Boolean withDelete) {
		this.withDelete = withDelete;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

}
