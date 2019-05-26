package com.jam2in.httpapi.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jam2in.httpapi.DAO.ApiDAO;
import com.jam2in.httpapi.response.ArcusBopBoolResponse;
import com.jam2in.httpapi.response.ArcusBopInsertBulkResponse;
import com.jam2in.httpapi.response.ArcusBopNotBoolResponse;
import com.jam2in.httpapi.response.ArcusLongSuccessResponse;
import com.jam2in.httpapi.response.ArcusSetBulkSuccessResponse;
import com.jam2in.httpapi.response.ArcusSuccessResponse;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.collection.*;
import net.spy.memcached.collection.ElementFlagFilter.*;
import net.spy.memcached.internal.BTreeStoreAndGetFuture;
import net.spy.memcached.internal.CollectionFuture;
import net.spy.memcached.internal.CollectionGetBulkFuture;
import net.spy.memcached.internal.SMGetFuture;
import net.spy.memcached.ops.CollectionOperationStatus;

@Service("apiService")
public class ApiServiceImpl implements ApiService {
/*

item 생성 async-bop-create
key, valueType, attributes
{
    "key": "Prefix:BTreeKey",
    "attributes": {
    	"flags" : 3,
    	"expireTime" : 10000
    }
}

element 삽입 async-bop-insert
key, bkey, eFlag, value, attributesForCreate
 {
    "key": "Prefix:BTreeKey",
    "bkey": 5,
    "value": "10",
    "eFlag": [1, 1, 1, 1],
    "attributesForCreate": {
    	"flags" : 3,
    	"expireTime" : 5000
    }
}
element 증감 async-bop-incr
key, subkey, by, initial, eFlag
{
    "key": "Prefix:BTreeKey",
    "subkey": 5,
    "by": 3,
    "eFlag": [1, 1, 1, 1],
    "initial": 70
}

element 조회 async-bop-get
key, bkey, withDelete, dropIfEmpty
{
    "key": "Prefix:BTreeKey",
    "bkey": 5,
    "withDelete": false,
    "dropIfEmpty": false
}
*/
	@Resource(name="apiDAO")
	private ApiDAO apiDAO;
	
	public ArcusSuccessResponse set(String key, int expireTime, Object value) {
		
		Future<Boolean> future = null;
		boolean result = false;	
		future = apiDAO.set(key, expireTime, (String)value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();
	}
	public ArcusSuccessResponse add(String key, int expireTime, Object value) {
		
		Future<Boolean> future = null;
		boolean result = false;
		
		future = apiDAO.add(key, expireTime, (String)value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();
	}
	public ArcusSuccessResponse replace(String key, int expireTime, Object value) {
		
		Future<Boolean> future = null;
		boolean result = false;
		
		future = apiDAO.replace(key, expireTime, (String)value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return new ArcusSuccessResponse();
	}
	public ArcusSuccessResponse prepend(long cas, String key, Object value) {
		
		Future<Boolean> future = null;
		boolean result = false;
		
		future = apiDAO.prepend(cas,key,value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();		
	}
	public ArcusSuccessResponse append(long cas, String key, Object value) {

		Future<Boolean> future = null;
		boolean result = false;
		future = apiDAO.append(cas, key, value);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();		
	}
	public ArcusSetBulkSuccessResponse setBulk(List<String> key, int expireTime, Object value) {
		
		Future<Map<String,CollectionOperationStatus>> future = null;
		Map<String,CollectionOperationStatus> resultMap = null;

		future = apiDAO.setBulk(key, expireTime, value);
		try {
			resultMap = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusSetBulkSuccessResponse response = new ArcusSetBulkSuccessResponse();
		response.setResult(resultMap);
		return response;
	}
	public ArcusSetBulkSuccessResponse setBulk(Map<String, Object> map, int expireTime) {
		
		Future<Map<String,CollectionOperationStatus>> future = null;
		Map<String,CollectionOperationStatus> resultMap = null;
		
		future = apiDAO.setBulk(map, expireTime);

		try {
			resultMap = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusSetBulkSuccessResponse response = new ArcusSetBulkSuccessResponse();
		response.setResult(resultMap);
		return response;
	}
	public ArcusSuccessResponse get(String key) {
		

		Future<Object> future = null;
		Object value = null;
		
		future = apiDAO.get(key);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		
		ArcusSuccessResponse response = new ArcusSuccessResponse();
		response.setResult(value);
		return response;
	}
	public ArcusSuccessResponse getBulk(Collection<String> key) {
		
		Future<Map<String,Object>> future = null;
		Map<String,Object> resultMap = null;
		
		future = apiDAO.getBulk(key);
		
		try {
			resultMap = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusSuccessResponse response = new ArcusSuccessResponse();
		response.setResult(resultMap);
		return response;
	}	
	public ArcusSuccessResponse getAttr(String key) {
		
		CollectionFuture<CollectionAttributes> future = null;
		CollectionAttributes result = null;
		
		future = apiDAO.getAttr(key);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | TimeoutException | ExecutionException e) {
			e.printStackTrace();
		}
		
		ArcusSuccessResponse response = new ArcusSuccessResponse();
		response.setResult(result);
		return response;
	}
	public ArcusLongSuccessResponse increase(String key, int by) {
		
		Future<Long> future = null;
		long value = 0;
		
		future = apiDAO.increase(key, by);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusLongSuccessResponse response = new ArcusLongSuccessResponse();
		response.setResult(value);
		return response;
	}
	public ArcusLongSuccessResponse increase(String key, int by, long def, int exp) {
		
		Future<Long> future = null;
		long value = 0;
		
		future = apiDAO.increase(key, by, def, exp);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusLongSuccessResponse response = new ArcusLongSuccessResponse();
		response.setResult(value);
		return response;
	}
	public ArcusLongSuccessResponse decrease(String key, int by) {

		Future<Long> future = null;
		long value = 0;
		future = apiDAO.decrease(key, by);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		ArcusLongSuccessResponse response = new ArcusLongSuccessResponse();
		response.setResult(value);
		return response;
	}
	public ArcusLongSuccessResponse decrease(String key, int by, long def, int exp) {
	
		Future<Long> future = null;
		long value = 0;
		
		future = apiDAO.decrease(key, by, def, exp);
		
		try {
			value = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		ArcusLongSuccessResponse response = new ArcusLongSuccessResponse();
		response.setResult(value);
		return response;
	}
	public ArcusSuccessResponse delete(String key) {
		
		Future<Boolean> future = null;
		boolean result = false;	
		future = apiDAO.delete(key);
		
		try {
			result = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return new ArcusSuccessResponse();
	}
	
	public ArcusBopBoolResponse bopCreate(String key, ElementValueType valueType, CollectionAttributes attributes) {
/*
 *
{
    "key": "Sample:EmptyBTree",
    "attributes": {
    	"flags" : 3,
    	"expireTime" : 60
    }
}*/
		
		CollectionFuture<Boolean> future = null;
		try {
			future = apiDAO.bopCreate(key, valueType, attributes);
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		Boolean result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
		}catch(TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopBoolResponse bopInsert(String key, Object bkey, byte[] eFlag, Object value, CollectionAttributes attributesForCreate) {
/*
 {
    "key": "Prefix:BTreeKey",
    "bkey": 1,
    "value":"This is a value.",
    "eFlag": [1, 1, 1, 1],
    "attributesForCreate": {
    	"flags" : 3,
    	"expireTime" : 60
    }
}   
{
    "key": "Prefix:BTreeKey",
    "bkey": [3, 2, 1, 0],
    "value":"This is a value.",
    "eFlag": [1, 1, 1, 1],
    "attributesForCreate": {
    	"flags" : 3,
    	"expireTime" : 60
    }
}

  */
		CollectionFuture<Boolean> future = null;		
		try{
			if(bkey instanceof Integer) {
				int scalarBkey = (int) bkey;
				future = apiDAO.bopInsert(key, scalarBkey, eFlag, value, attributesForCreate);
			}else {				
				System.out.println(bkey);
				System.out.println(value);
				ArrayList<Integer> al = (ArrayList<Integer>)bkey;
			
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				
				for (int element : al) {
				    out.writeUTF(Integer.toString(element));
				}
				byte[] bytesBkey = baos.toByteArray();
				future = apiDAO.bopInsert(key, bytesBkey , eFlag, value, attributesForCreate);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Boolean result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return new ArcusBopBoolResponse(result, future.getOperationStatus().getResponse());
	}


	public ArcusBopInsertBulkResponse bopPipedInsertBulk(String key, Map<Long, Object> elementsWithMap,  CollectionAttributes attributesForCreate) {
		/*
		 {
		    "key": "Prefix:BTreeKey",
		    "elementWithMap": { "5" : "value1", "10" : "value2", "15" : "value3" },
		    "attributesForCreate": {
		    	"flags" : 3,
		    	"expireTime" : 60
		    }
		 }
		*/
		CollectionFuture<Map<Integer, CollectionOperationStatus>> future = null;		
		try{
			future = apiDAO.bopPipedInsertBulk(key, elementsWithMap, attributesForCreate);
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
		Map<Integer, CollectionOperationStatus> result = null;
		Map<Object, CollectionOperationStatus> convertResult = new HashMap<Object, CollectionOperationStatus>();
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			for (Map.Entry<Integer, CollectionOperationStatus> entry : result.entrySet()) {
				convertResult.put(entry.getKey(), entry.getValue());
			}
		}catch(TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}		
		
		return new ArcusBopInsertBulkResponse(convertResult, elementsWithMap);
	}
	
//	@SuppressWarnings({ "unchecked", "null" })
	@SuppressWarnings("unchecked")
	public ArcusBopInsertBulkResponse bopInsertBulk(List<String> keyList, Object bkey, byte[] eflag, Object value, CollectionAttributes attributesForCreate) {
		/*
		 {
		    "keyList": { "Prefix:BTreeKey", "Prefix:BTreeKey2" },
		    "bkey": 1,
		    "value":"This is a value.",
		    "eFlag": [1, 1, 1, 1],
		    "attributesForCreate": {
		    	"flags" : 3,
		    	"expireTime" : 60
		    }
		 }   
		 {
		    "keyList": { "Prefix:BTreeKey", "Prefix:BTreeKey2" },
		    "bkey": [3, 2, 1, 0],
		    "value":"This is a value.",
		    "eFlag": [1, 1, 1, 1],
		    "attributesForCreate": {
		    	"flags" : 3,
		    	"expireTime" : 60
		    }
		 }
		*/
		Future<Map<String, CollectionOperationStatus>> future = null;		
		try{
			if(bkey instanceof Integer) {
				int scalarBkey = (int) bkey;
				future = apiDAO.bopInsertBulk(keyList, scalarBkey, eflag, value, attributesForCreate);
			}else {				
				ArrayList<Integer> al = (ArrayList<Integer>)bkey;
			
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				
				for (int element : al) {
				    out.writeUTF(Integer.toString(element));
				}
				byte[] bytesBkey = baos.toByteArray();
				future = apiDAO.bopInsertBulk(keyList, bytesBkey , eflag, value, attributesForCreate);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Map<String, CollectionOperationStatus> result = null;
		Map<Object, CollectionOperationStatus> convertResult = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			for (Map.Entry<String, CollectionOperationStatus> entry : result.entrySet()) {
				convertResult.put(entry.getKey(), entry.getValue());
			}		
		}catch(TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return new ArcusBopInsertBulkResponse(convertResult, null);
	}

	// should alter
//	public ArcusBopBoolResponse bopInsertAndGetTrimmed(String key, Object bkey, byte[] eFlag, Object value, CollectionAttributes attributesForCreate) {
//		BTreeStoreAndGetFuture<Boolean, Object> future = apiDAO.asyncBopInsertAndGetTrimmed(key, bkey, eFlag, value, attributesForCreate); // key, 2000, null, "val", null
//		boolean succeeded = future.get();
//		Element<Object> element = future.getElement();
//	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopBoolResponse bopUpsert(String key, Object bkey, byte[] eFlag, Object value, CollectionAttributes attributesForCreate) {
		CollectionFuture<Boolean> future = null;
		
		try {
			if(bkey instanceof Integer) {
				int scalarBkey = (int) bkey;
				future = apiDAO.bopUpsert(key, scalarBkey, eFlag, value, attributesForCreate);
			}else {				
				ArrayList<Integer> al = (ArrayList<Integer>)bkey;
			
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				
				for (int element : al) {
				    out.writeUTF(Integer.toString(element));
				}
				byte[] bytesBkey = baos.toByteArray();
				future = apiDAO.bopUpsert(key, bytesBkey, eFlag, value, attributesForCreate);
			}
		}catch(IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Boolean result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopBoolResponse bopUpdate(String key, Object bkey, String eFlagUpdate, Object flag, BitWiseOperands bitOp, Integer eFlagOffset, Object value) {
/*
{
    "key": "Prefix:BTreeKey",
    "bkey": [3, 2, 1, 0],
    "value":"This is a value.2",
    "eFlag": null
}
*/
		CollectionFuture<Boolean> future = null;
		ElementFlagUpdate realeFlagUpdate = null;
		
		// only changing value and leaving eflag value as is
		if(flag.equals(null) && eFlagOffset.equals(null) && bitOp.equals(null) && eFlagUpdate.equals(null)) {
			realeFlagUpdate = null;
		}
		// replacing the entire eflag value
		else if(!flag.equals(null) && eFlagOffset.equals(null) && bitOp.equals(null)) {
			ArrayList<Integer> al = (ArrayList<Integer>)flag;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(baos);
			
			for (int element : al) {
			    try {
					out.writeUTF(Integer.toString(element));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			byte[] bytesFlag = baos.toByteArray();
			
			realeFlagUpdate = new ElementFlagUpdate(bytesFlag);
		}
		// replacing the partial eflag value 
		else if(!flag.equals(null) && !eFlagOffset.equals(null) && !bitOp.equals(null) ) {
			ArrayList<Integer> al = (ArrayList<Integer>)flag;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(baos);
			
			for (int element : al) {
			    try {
					out.writeUTF(Integer.toString(element));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			byte[] bytesFlag = baos.toByteArray();
			
			realeFlagUpdate = new ElementFlagUpdate((int)eFlagOffset, bitOp, bytesFlag);
		}
		
		try {
			if(bkey instanceof Integer) {
				int scalarBkey = (int) bkey;
				if(eFlagUpdate.equals("RESET_FLAG")) {
					// deleting eflag and leaving the value as is
					future = apiDAO.bopUpdate(key, scalarBkey, ElementFlagUpdate.RESET_FLAG, value);
				}else {
					future = apiDAO.bopUpdate(key, scalarBkey, realeFlagUpdate, value);					
				}
			}else {
				ArrayList<Integer> al = (ArrayList<Integer>)bkey;
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				
				for (int element : al) {
				    out.writeUTF(Integer.toString(element));
				}
				byte[] bytesBkey = baos.toByteArray();
				if(eFlagUpdate.equals("RESET_FLAG")) {
					// deleting eflag and leaving the value as is
					future = apiDAO.bopUpdate(key, bytesBkey, ElementFlagUpdate.RESET_FLAG, value);
				}else {
					future = apiDAO.bopUpdate(key, bytesBkey, realeFlagUpdate, value);					
				}			}
		}catch(IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Boolean result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopBoolResponse(result, future.getOperationStatus().getResponse());
	}
	@SuppressWarnings("unchecked")
	public ArcusBopBoolResponse bopDelete(String key, Object from, Object to, Integer count, Boolean dropIfEmpty) {//, String eFlagFilter, Object compValue, Integer count, Boolean dropIfEmpty) {
		CollectionFuture<Boolean> future = null;
//		ElementFlagFilter realeFlagFilter = null;

//		if(eFlagFilter.equals("DO_NOT_FILTER") && compValue.equals(null))
//			realeFlagFilter = ElementFlagFilter.DO_NOT_FILTER;
//		else if(eFlagFilter.equals("Equal")) {
//			byte[] tempCompValue = (byte[])compValue;
//			realeFlagFilter = new ElementFlagFilter(ElementFlagFilter.CompOperands.Equal, tempCompValue);
//		}
//		else if(eFlagFilter.equals("NotEqual")) {
//			byte[] tempCompValue = (byte[])compValue;
//			realeFlagFilter = new ElementFlagFilter(ElementFlagFilter.CompOperands.NotEqual, tempCompValue);			
//		}
//		else if(eFlagFilter.equals("LessThan")) {
//			byte[] tempCompValue = (byte[])compValue;
//			realeFlagFilter = new ElementFlagFilter(ElementFlagFilter.CompOperands.LessThan, tempCompValue);
//		}
//		else if(eFlagFilter.equals("LessOrEqual")) {
//			byte[] tempCompValue = (byte[])compValue;
//			realeFlagFilter = new ElementFlagFilter(ElementFlagFilter.CompOperands.LessOrEqual, tempCompValue);
//		}
//		else if(eFlagFilter.equals("GreaterThan")) {
//			byte[] tempCompValue = (byte[])compValue;
//			realeFlagFilter = new ElementFlagFilter(ElementFlagFilter.CompOperands.GreaterThan, tempCompValue);
//		}
//		else if(eFlagFilter.equals("GreaterOrEqual")) {
//			byte[] tempCompValue = (byte[])compValue;
//			realeFlagFilter = new ElementFlagFilter(ElementFlagFilter.CompOperands.GreaterOrEqual, tempCompValue);
//		}
//		else if(eFlagFilter.equals("AND")) {
//			byte[] tempCompValue = (byte[])compValue;
//			//The method setBitOperands(ElementFlagFilter.BitWiseOperands, byte[]) is undefined for the type ElementFlagFilter
//			realeFlagFilter.setBitOperands(ElementFlagFilter.BitWiseOperands.AND, tempCompValue);
//		}
		
		try {
			if(from instanceof Integer) {
				int scalarFrom = (int) from;
				int scalarTo = (int) to;
				future = apiDAO.bopDelete(key, scalarFrom, scalarTo, ElementFlagFilter.DO_NOT_FILTER, count, dropIfEmpty);
			}else {
				ArrayList<Integer> alF = (ArrayList<Integer>)from;
				ArrayList<Integer> alT = (ArrayList<Integer>)to;
				
				ByteArrayOutputStream baosF = new ByteArrayOutputStream();
				DataOutputStream outF = new DataOutputStream(baosF);
				ByteArrayOutputStream baosT = new ByteArrayOutputStream();
				DataOutputStream outT = new DataOutputStream(baosT);
				
				for (int element : alF) {
				    outF.writeUTF(Integer.toString(element));
				}
				for (int element : alT) {
				    outT.writeUTF(Integer.toString(element));
				}
				byte[] bytesFrom = baosF.toByteArray();
				byte[] bytesTo = baosT.toByteArray();
				future = apiDAO.bopDelete(key, bytesFrom, bytesTo, ElementFlagFilter.DO_NOT_FILTER, count, dropIfEmpty);
			}
		}catch(IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopBoolResponse(result, future.getOperationStatus().getResponse());
	}
	

	@SuppressWarnings("unchecked")
	public ArcusBopBoolResponse bopDelete(String key, Object bkey, Boolean dropIfEmpty) {
		/*{
    "key": "Prefix:BTreeKey",
    "bkey": [3, 2, 1, 0],
    "dropIfEmtpy" : true
}*/
		CollectionFuture<Boolean> future = null;
		
		try {
			if(bkey instanceof Integer) {
				int scalarBkey = (int) bkey;
				future = apiDAO.bopDelete(key, scalarBkey, ElementFlagFilter.DO_NOT_FILTER, dropIfEmpty);
			}else {
				ArrayList<Integer> al = (ArrayList<Integer>)bkey;
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				
				for (int element : al) {
				    out.writeUTF(Integer.toString(element));
				}
				byte[] bytesBkey = baos.toByteArray();
				future = apiDAO.bopDelete(key, bytesBkey, ElementFlagFilter.DO_NOT_FILTER, dropIfEmpty);//eFlagFilter request 받는 방법 생각해보기
			}
		}catch(IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Boolean result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopIncr(String key, Object subkey, Integer by, Long initial, byte[] eFlag) {
		CollectionFuture<Long> future = null;
		System.out.println(key+" "+subkey+" "+by);
		try {
			if(subkey instanceof Integer) {
				int scalarSubkey = (int) subkey;
				future = apiDAO.bopIncr(key, scalarSubkey, by, initial, eFlag);
			}else {
				ArrayList<Integer> al = (ArrayList<Integer>)subkey;
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				
				for (int element : al) {
				    out.writeUTF(Integer.toString(element));
				}
				byte[] bytesSubkey = baos.toByteArray();
				future = apiDAO.bopIncr(key, bytesSubkey, by);
			}
		}catch(IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Long result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopIncr(String key, Object bkey, Integer by) {
		CollectionFuture<Long> future = null;
		
		try {
			if(bkey instanceof Integer) {
				int scalarBkey = (int) bkey;
				System.out.println(key+" "+bkey+" "+by);
				future = apiDAO.bopIncr(key, scalarBkey, by);
			}else {
				ArrayList<Integer> al = (ArrayList<Integer>)bkey;
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				
				for (int element : al) {
				    out.writeUTF(Integer.toString(element));
				}
				byte[] bytesSubkey = baos.toByteArray();
				future = apiDAO.bopIncr(key, bytesSubkey, by);
			}
		}catch(IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Long result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopDecr(String key, Object subkey, Integer by, Long initial, byte[] eFlag) {
		CollectionFuture<Long> future = null;
		
		try {
			if(subkey instanceof Integer) {
				int scalarSubkey = (int) subkey;
				int scalarBy = (int)by;
				long scalarInitial = (long)initial;
				future = apiDAO.bopDecr(key, scalarSubkey, scalarBy, scalarInitial, eFlag);
			}else {
				ArrayList<Integer> al = (ArrayList<Integer>)subkey;
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				
				for (int element : al) {
				    out.writeUTF(Integer.toString(element));
				}
				byte[] bytesSubkey = baos.toByteArray();
				future = apiDAO.bopDecr(key, bytesSubkey, by);
			}
		}catch(IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Long result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopDecr(String key, Object bkey, Integer by) {
		CollectionFuture<Long> future = null;
		
		try {
			if(bkey instanceof Integer) {
				int scalarBkey = (int) bkey;
				future = apiDAO.bopDecr(key, scalarBkey, by);
			}else {
				ArrayList<Integer> al = (ArrayList<Integer>)bkey;
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				
				for (int element : al) {
				    out.writeUTF(Integer.toString(element));
				}
				byte[] bytesSubkey = baos.toByteArray();
				future = apiDAO.bopDecr(key, bytesSubkey, by);
			}
		}catch(IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Long result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopGetItemCount(String key, Object from, Object to) {
/*
{
    "key": "Prefix:BTreeKey",
    "from" : 0,
    "to" : 100
}
*/
		CollectionFuture<Integer> future = null;
		
		try {
			if(from instanceof Integer) {
				int scalarFrom = (int) from;
				int scalarTo = (int) to;
				future = apiDAO.bopGetItemCount(key, scalarFrom, scalarTo, ElementFlagFilter.DO_NOT_FILTER);
				
			}else {
				ArrayList<Integer> alF = (ArrayList<Integer>)from;
				ArrayList<Integer> alT = (ArrayList<Integer>)to;
				
				ByteArrayOutputStream baosF = new ByteArrayOutputStream();
				DataOutputStream outF = new DataOutputStream(baosF);
				ByteArrayOutputStream baosT = new ByteArrayOutputStream();
				DataOutputStream outT = new DataOutputStream(baosT);
				
				for (int element : alF) {
				    outF.writeUTF(Integer.toString(element));
				}
				for (int element : alT) {
				    outT.writeUTF(Integer.toString(element));
				}
				byte[] bytesFrom = baosF.toByteArray();
				byte[] bytesTo = baosT.toByteArray();
				future = apiDAO.bopGetItemCount(key, bytesFrom, bytesTo, ElementFlagFilter.DO_NOT_FILTER);
			}
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Integer result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
			System.out.println(result);
			System.out.println(future.getOperationStatus().getResponse());
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopGet(String key, Object from, Object to, Integer offset, Integer count, Boolean withDelete, Boolean dropIfEmpty) {
		/*
		{
		    "key": "Prefix:BTreeKey",
		    "from" : 3,
		    "to" : 1,
		    "offset" : 2,
		    "count" : 3,
		    "withDelete" : false,
		    "dropIfEmpty"  : false
		}
		 * */
		ElementFlagFilter filter = new ElementFlagFilter(CompOperands.Equal, new byte[] {1,1});
		if(from instanceof Integer) {
			CollectionFuture<Map<Long, Element<Object>>> future = null;

			try {
				int scalarFrom = (int) from;
				int scalarTo = (int) to;
				future = apiDAO.bopGet(key, scalarFrom, scalarTo, filter, offset, count, withDelete, dropIfEmpty);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}
			Map<Long, Element<Object>> result = null;
			try {
				result = future.get(1000L, TimeUnit.MILLISECONDS);
				System.out.println(result);
				
				CollectionResponse response = future.getOperationStatus().getResponse();
				if(response.equals(CollectionResponse.NOT_FOUND)) {
					System.out.println("Key가 없습니다.(Key에 저장된 B+tree가 없음)");
				}else if(response.equals(CollectionResponse.NOT_FOUND_ELEMENT)) {
					System.out.println("Key에 B+tree는 존재하지만 저장된 값 중 조건에 맞는 것이 없음.");
				}
			}catch(TimeoutException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}catch(ExecutionException e) {
				e.printStackTrace();
			}
			return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());

		}else {
			CollectionFuture<Map<ByteArrayBKey, Element<Object>>> future = null;

			try {
				ArrayList<Integer> alF = (ArrayList<Integer>)from;
				ArrayList<Integer> alT = (ArrayList<Integer>)to;
				
				ByteArrayOutputStream baosF = new ByteArrayOutputStream();
				DataOutputStream outF = new DataOutputStream(baosF);
				ByteArrayOutputStream baosT = new ByteArrayOutputStream();
				DataOutputStream outT = new DataOutputStream(baosT);
				
				for (int element : alF) {
				    outF.writeUTF(Integer.toString(element));
				}
				for (int element : alT) {
				    outT.writeUTF(Integer.toString(element));
				}
				byte[] bytesFrom = baosF.toByteArray();
				byte[] bytesTo = baosT.toByteArray();
				future = apiDAO.bopGet(key, bytesFrom, bytesTo, filter, offset, count, withDelete, dropIfEmpty);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			Map<ByteArrayBKey, Element<Object>> result = null;
			try {
				result = future.get(1000L, TimeUnit.MILLISECONDS);
				System.out.println(result);
				
				CollectionResponse response = future.getOperationStatus().getResponse();
				if(response.equals(CollectionResponse.NOT_FOUND)) {
					System.out.println("Key가 없습니다.(Key에 저장된 B+tree가 없음)");
				}else if(response.equals(CollectionResponse.NOT_FOUND_ELEMENT)) {
					System.out.println("Key에 B+tree는 존재하지만 저장된 값 중 조건에 맞는 것이 없음.");
				}
			}catch(TimeoutException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}catch(ExecutionException e) {
				e.printStackTrace();
			}
			return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
		}
	}
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopGet(String key, Object bkey, Boolean withDelete, Boolean dropIfEmpty) {
		ElementFlagFilter filter = new ElementFlagFilter(CompOperands.Equal, new byte[] {1,1});
		if(bkey instanceof Integer) {
			CollectionFuture<Map<Long, Element<Object>>> future = null;

			try {
				int scalarBkey = (int) bkey;
				future = apiDAO.bopGet(key, scalarBkey, filter, withDelete, dropIfEmpty);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}
			Map<Long, Element<Object>> result = null;
			try {
				result = future.get(1000L, TimeUnit.MILLISECONDS);
				System.out.println(result);
				
				CollectionResponse response = future.getOperationStatus().getResponse();
				if(response.equals(CollectionResponse.NOT_FOUND)) {
					System.out.println("Key가 없습니다.(Key에 저장된 B+tree가 없음)");
				}else if(response.equals(CollectionResponse.NOT_FOUND_ELEMENT)) {
					System.out.println("Key에 B+tree는 존재하지만 저장된 값 중 조건에 맞는 것이 없음.");
				}
			}catch(TimeoutException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}catch(ExecutionException e) {
				e.printStackTrace();
			}
			return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());

		}else {
			CollectionFuture<Map<ByteArrayBKey, Element<Object>>> future = null;

			try {
				ArrayList<Integer> al = (ArrayList<Integer>)bkey;
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream outF = new DataOutputStream(baos);
				
				for (int element : al) {
				    outF.writeUTF(Integer.toString(element));
				}
				byte[] bytes = baos.toByteArray();
				future = apiDAO.bopGet(key, bytes, filter, withDelete, dropIfEmpty);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			Map<ByteArrayBKey, Element<Object>> result = null;
			try {
				result = future.get(1000L, TimeUnit.MILLISECONDS);
				System.out.println(result);
				
				CollectionResponse response = future.getOperationStatus().getResponse();
				if(response.equals(CollectionResponse.NOT_FOUND)) {
					System.out.println("Key가 없습니다.(Key에 저장된 B+tree가 없음)");
				}else if(response.equals(CollectionResponse.NOT_FOUND_ELEMENT)) {
					System.out.println("Key에 B+tree는 존재하지만 저장된 값 중 조건에 맞는 것이 없음.");
				}
			}catch(TimeoutException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}catch(ExecutionException e) {
				e.printStackTrace();
			}
			return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopGetBulk(List<String> keyList, Object from, Object to, Integer offset, Integer count) {
		/*
		{
		    "keyList": { "Prefix:BTreeKey", "Prefix:BTreeKey2" }, 
		    "from" : 3,
		    "to" : 1,
		    "eFlagFilter" : "DO_NOT_FILTER",
		    "offset" : 0,
		    "count" : 3
		}
		{
		    "keyList": { "Prefix:BTreeKey", "Prefix:BTreeKey2" }, 
		    "from" : [3, 2, 1, 0],
		    "to" : [0, 1, 2, 3],
		    "eFlagFilter" : "DO_NOT_FILTER",
		    "offset" : 0,
		    "count" : 3
		}
		 * */
		if(from instanceof Integer) {
			CollectionGetBulkFuture<Map<String, BTreeGetResult<Long, Object>>> future = null;
			try {
				int scalarFrom = (int) from;
				int scalarTo = (int) to;
				future = apiDAO.bopGetBulk(keyList, scalarFrom, scalarTo, ElementFlagFilter.DO_NOT_FILTER, offset, count);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}
			
			Map<String, BTreeGetResult<Long, Object>> result = null;
			try {
				result = future.get(1000L, TimeUnit.MILLISECONDS);
			}catch(TimeoutException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}catch(ExecutionException e) {
				e.printStackTrace();
			}
			return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
		}else {
			CollectionGetBulkFuture<Map<String, BTreeGetResult<ByteArrayBKey, Object>>> future = null;
			try {
				ArrayList<Integer> alF = (ArrayList<Integer>)from;
				ArrayList<Integer> alT = (ArrayList<Integer>)to;
				
				ByteArrayOutputStream baosF = new ByteArrayOutputStream();
				DataOutputStream outF = new DataOutputStream(baosF);
				ByteArrayOutputStream baosT = new ByteArrayOutputStream();
				DataOutputStream outT = new DataOutputStream(baosT);
				
				for (int element : alF) {
				    outF.writeUTF(Integer.toString(element));
				}
				for (int element : alT) {
				    outT.writeUTF(Integer.toString(element));
				}
				byte[] bytesFrom = baosF.toByteArray();
				byte[] bytesTo = baosT.toByteArray();
				future = apiDAO.bopGetBulk(keyList, bytesFrom, bytesTo, ElementFlagFilter.DO_NOT_FILTER, offset, count);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			Map<String, BTreeGetResult<ByteArrayBKey, Object>> result = null;
			try {
				result = future.get(1000L, TimeUnit.MILLISECONDS);
			}catch(TimeoutException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}catch(ExecutionException e) {
				e.printStackTrace();
			}
			return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopSMGet(List<String> keyList, Object from, Object to, Integer count, SMGetMode smgetMode) {
		/*
		{
		    "keyList": { "Prefix:BTreeKey", "Prefix:BTreeKey2" }, 
		    "from" : 3,
		    "to" : 1,
		    "eFlagFilter" : "DO_NOT_FILTER",
		    "count" : 3,
		    "smgetMode" : "DUPLICATE"
		}
		{
		    "keyList": { "Prefix:BTreeKey", "Prefix:BTreeKey2" }, 
		    "from" : [3, 2, 1, 0],
		    "to" : [0, 1, 2, 3],
		    "eFlagFilter" : "DO_NOT_FILTER",
		    "count" : 3,
		    "smgetMode" : "UNIQUE"
		}
		*/
		SMGetFuture<List<SMGetElement<Object>>> future = null;

		if(from instanceof Integer) {
			try {
				int scalarFrom = (int) from;
				int scalarTo = (int) to;
				future = apiDAO.bopSMGet(keyList, scalarFrom, scalarTo, ElementFlagFilter.DO_NOT_FILTER, count, smgetMode);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}
		}else {
			try {
				ArrayList<Integer> alF = (ArrayList<Integer>)from;
				ArrayList<Integer> alT = (ArrayList<Integer>)to;
				
				ByteArrayOutputStream baosF = new ByteArrayOutputStream();
				DataOutputStream outF = new DataOutputStream(baosF);
				ByteArrayOutputStream baosT = new ByteArrayOutputStream();
				DataOutputStream outT = new DataOutputStream(baosT);
				
				for (int element : alF) {
				    outF.writeUTF(Integer.toString(element));
				}
				for (int element : alT) {
				    outT.writeUTF(Integer.toString(element));
				}
				byte[] bytesFrom = baosF.toByteArray();
				byte[] bytesTo = baosT.toByteArray();
				future = apiDAO.bopSMGet(keyList, bytesFrom, bytesTo, ElementFlagFilter.DO_NOT_FILTER, count, smgetMode);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		List<SMGetElement<Object>> result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopFindPosition(String key, Object bkey, BTreeOrder order) {
		/*
		{
		    "keyList": "Prefix:BTreeKey", 
		    "bkey" : 3,
		    "order" : "ASC"
		}
		{
		    "keyList": "Prefix:BTreeKey", 
		    "from" : [3, 2, 1, 0],
		    "order" : "DESC"
		}
		*/
		CollectionFuture<Integer> future = null;

		if(bkey instanceof Integer) {
			try {
				int scalarBkey = (int) bkey;
				future = apiDAO.bopFindPosition(key, scalarBkey, order);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}
		}else {
			try {
				ArrayList<Integer> alB = (ArrayList<Integer>)bkey;
				
				ByteArrayOutputStream baosB = new ByteArrayOutputStream();
				DataOutputStream outB = new DataOutputStream(baosB);

				for (int element : alB) {
				    outB.writeUTF(Integer.toString(element));
				}

				byte[] bytesBkey = baosB.toByteArray();
				future = apiDAO.bopFindPosition(key, bytesBkey, order);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Integer result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopGetByPosition(String key, BTreeOrder order, Integer position) {
		/*
		{
		    "keyList": "Prefix:BTreeKey", 
		    "order" : "ASC"
		    "position" : 3
		}

		*/
		CollectionFuture<Map<Integer, Element<Object>>> future = null;

		try {
			int scalarPosition = (int) position;
			future = apiDAO.bopGetByPosition(key, order, scalarPosition);
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
			
		Map<Integer, Element<Object>> result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopGetByPosition(String key, BTreeOrder order, Object from, Object to) {
		/*
		{
		    "keyList": "Prefix:BTreeKey", 
		    "order" : "DESC"
		    "from" : 5
		    "to" : 3
		}
		*/
		CollectionFuture<Map<Integer, Element<Object>>> future = null;

		try {
			int scalarFrom = (int) from;
			int scalarTo = (int) to;
			future = apiDAO.bopGetByPosition(key, order, scalarFrom, scalarTo);
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
			
		Map<Integer, Element<Object>> result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
	@SuppressWarnings("unchecked")
	public ArcusBopNotBoolResponse bopFindPositionWithGet(String key, Object bkey, BTreeOrder order, Integer count) {
		/*
		{
		    "keyList": "Prefix:BTreeKey", 
		    "bkey" : 3,
		    "order" : "ASC",
		    "count" : 3
		}
		{
		    "keyList": "Prefix:BTreeKey", 
		    "from" : [3, 2, 1, 0],
		    "order" : "DESC",
		    "count" : 3
		}
		*/
		CollectionFuture<Map<Integer, Element<Object>>> future = null;

		if(bkey instanceof Integer) {
			try {
				int scalarBkey = (int) bkey;
				future = apiDAO.bopFindPositionWithGet(key, scalarBkey, order, count);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}
		}else {
			try {
				ArrayList<Integer> alB = (ArrayList<Integer>)bkey;
				
				ByteArrayOutputStream baosB = new ByteArrayOutputStream();
				DataOutputStream outB = new DataOutputStream(baosB);

				for (int element : alB) {
				    outB.writeUTF(Integer.toString(element));
				}

				byte[] bytesBkey = baosB.toByteArray();
				future = apiDAO.bopFindPositionWithGet(key, bytesBkey, order, count);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Map<Integer, Element<Object>> result = null;
		try {
			result = future.get(1000L, TimeUnit.MILLISECONDS);
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(ExecutionException e) {
			e.printStackTrace();
		}
		return new ArcusBopNotBoolResponse(result, future.getOperationStatus().getResponse());
	}
	
}
