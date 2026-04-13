package com.gabrielsoac.homework_generator.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface HomeWorkAgent {
    JsonNode generateQuestions(String text) throws JsonMappingException, JsonProcessingException;
}
