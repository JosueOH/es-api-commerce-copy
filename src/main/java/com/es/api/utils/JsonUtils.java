package com.es.api.utils;

import com.es.api.messages.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {

  private final ObjectMapper mapper;

  // ~ CONSTRUCTOR(S) -------------------------------------------------------

  public JsonUtils(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  // ~ METHOD(S) ------------------------------------------------------------

  public String toJson(Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new InternalServerErrorException(ErrorMessage.PARSING_OBJECT_TO_JSON);
    }
  }

  public <T> T fromJson(String json, Class<T> responseType) {
    try {
      return mapper.readValue(json, responseType);
    } catch (JsonProcessingException e) {
      throw new InternalServerErrorException(ErrorMessage.PARSING_OBJECT_TO_JSON);
    }
  }

  public ObjectNode getNodeObject() {
    return mapper.createObjectNode();
  }

}
