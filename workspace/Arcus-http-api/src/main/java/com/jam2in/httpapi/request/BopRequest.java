package com.jam2in.httpapi.request;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.spy.memcached.collection.*;
import net.spy.memcached.collection.ElementFlagFilter.BitWiseOperands;
import net.spy.memcached.collection.ElementFlagFilter.CompOperands;

public class BopRequest {
	String key = null;
	List<String> keyList = null;

	//ElementValueType valueType = null;
	
	CollectionAttributes attributes = null;
	CollectionAttributes attributesForCreate = null;
	Object bkey = null;
	byte[] eFlag = null;
	Object value = null;
	
	BitWiseOperands bitOp = null;
	public BitWiseOperands getBitOp() {
		return bitOp;
	}

	public void setBitOp(BitWiseOperands bitOp) {
		this.bitOp = bitOp;
	}

	public Integer geteFlagOffset() {
		return eFlagOffset;
	}

	public void seteFlagOffset(Integer eFlagOffset) {
		this.eFlagOffset = eFlagOffset;
	}

	Integer eFlagOffset = null;
	
	Object compValue = null;
	Object flag = null;
	String eFlagUpdate = null;
	String eFlagFilter = null;
	
	Boolean dropIfEmpty = null;
	Object from = null;
	Object to = null;
	Integer count = null; // item attribute & parameter
	Integer by = null;
	Integer offset = null;
	Object subkey = null;
	Long initial = null;
	Boolean withDelete = null;
	Map<Long, Object> elementsWithMap = null;
	SMGetMode smgetMode = null;
	BTreeOrder order = null;
	Integer position = null;
	
	public String getKey() {
		return key;
	}

	public List<String> getKeyList() {
		return keyList;
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
	public String geteFlagUpdate() {
		return eFlagUpdate;
	}
	public Object geteFlagFilter() {
		return eFlagFilter;
	}
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
	public Map<Long, Object> getElementsWithMap() {
		return elementsWithMap;
	}
	public SMGetMode getSMGetMode() {
		return smgetMode;
	}
	public BTreeOrder getOrder() {
		return order;
	}
	public Integer getPosition() {
		return position;
	}
	public Object getCompValue() {
		return compValue;
	}
	public Object getFlag() {
		return flag;
	}

	
	/***********************************************/
	public void setFlag(Object flag) {
		this.flag = flag;
	}
	public void setCompValue(Object compValue) {
		this.compValue = compValue;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}
	
	/*
	 * public void setValueType(ElementValueType valueType) { this.valueType =
	 * valueType; }
	 */
	public void setAttributes(CollectionAttributes attributes) {
		this.attributes = attributes;
	}
	public void setElementsWithMap(Map<Long, Object> elements) {
		this.elementsWithMap = elements;
	}
	public void setBkey(Object bkey) {
		this.bkey = bkey;
	}
	public void seteFlag(byte[] eFlag) {
		this.eFlag = eFlag;//Arrays.toString(eFlag);//(byte[]) eFlag;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public void seteFlagUpdate(String eFlagUpdate) {
		this.eFlagUpdate = eFlagUpdate;
	}
	public void seteFlagFilter(String stringFilter) {
		if(stringFilter.isEmpty())
			eFlagFilter = "DO_NOT_FILTER";
		else 
			eFlagFilter = stringFilter;	
	}
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
	
	public void setSMGetMode(SMGetMode smgetMode) {
		this.smgetMode = smgetMode;
	}

	public void setBtreeOrder(BTreeOrder order) {
		this.order = order;
	}
	
	public void setPosition(Integer position) {
		this.position = position;
	}
}
