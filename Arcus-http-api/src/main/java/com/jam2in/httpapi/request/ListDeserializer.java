package com.jam2in.httpapi.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ListDeserializer  extends JsonDeserializer<List<String>>{
	
	@Override
	public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException{
		List<String> result = new ArrayList<String>();
		ObjectMapper mapper = (ObjectMapper)jp.getCodec();
		JsonNode rootNode = mapper.readTree(jp);
		
		for(JsonNode node : rootNode) {
			result.add(mapper.writeValueAsString(node));
		}
		
		return result;
		
	}
}
