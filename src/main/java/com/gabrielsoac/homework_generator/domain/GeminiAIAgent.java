package com.gabrielsoac.homework_generator.domain;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielsoac.homework_generator.util.PromptHelper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class GeminiAIAgent {    
    @Autowired
    private Client client = new Client();
    private PromptHelper promptHelper = new PromptHelper();

    public HomeWork generateHomeWork(String text) throws JsonMappingException, JsonProcessingException {
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

        System.out.println(tree);
        tree.get("title");

        return new HomeWork(jsonCleared, null);
    }


    




}
