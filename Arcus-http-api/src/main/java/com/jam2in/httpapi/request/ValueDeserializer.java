package com.jam2in.httpapi.request;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ValueDeserializer extends JsonDeserializer<String>{
	  @Override
	  public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		  String result;
	      ObjectMapper mapper = (ObjectMapper) jp.getCodec();
	      JsonNode node = mapper.readTree(jp);
	      result = mapper.writeValueAsString(node);	      

	      return result;
	  }
}
