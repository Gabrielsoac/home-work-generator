package com.gabrielsoac.homework_generator.domain.agents;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielsoac.homework_generator.interfaces.HomeWorkAgent;
import com.gabrielsoac.homework_generator.util.PromptHelper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class GeminiAIAgent implements HomeWorkAgent {
    private Client client = new Client();
    private PromptHelper promptHelper = new PromptHelper();

    public JsonNode generateQuestions(String text) throws JsonMappingException, JsonProcessingException {
        GenerateContentResponse response =
            client.models.generateContent(
                "gemini-3-flash-preview",
                promptHelper.createPromptForGenerateHomeWork(text),
                null);
        String modelResponse = response.text();

        ObjectMapper mapper = new ObjectMapper();

        String jsonCleared = 
            modelResponse
                .replace("```json", "")
                .replace("```", "")
                .trim();
        
        JsonNode tree = mapper.readTree(jsonCleared);
        return tree;
    }


    




}
