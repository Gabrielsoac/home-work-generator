package com.gabrielsoac.homework_generator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.gabrielsoac.homework_generator.domain.HomeWork;
import com.gabrielsoac.homework_generator.domain.Question;
import com.gabrielsoac.homework_generator.domain.agents.GeminiAIAgent;
import com.gabrielsoac.homework_generator.domain.files.FileContext;
import com.gabrielsoac.homework_generator.domain.files.FileType;
import com.gabrielsoac.homework_generator.interfaces.FileProcessor;

@Service
public class HomeWorkService {
    FileProcessor pdfFileProcessor = new ProcessPDFFile();
    FileProcessor docxFileProcessor = new ProcessDocxFile();
    GeminiAIAgent geminiAgent = new GeminiAIAgent();

    Map<FileType, FileProcessor> fileProcessors = 
        new HashMap<
            FileType,
            FileProcessor
        >(Map.of(FileType.pdf, pdfFileProcessor, FileType.docx, docxFileProcessor));
    
    public HomeWork createHomeWorkByFile(MultipartFile file) throws JsonMappingException, JsonProcessingException{
        try {
            FileContext fileContext = new FileContext(file);
            String fileText = fileProcessors.get(fileContext.getType()).getFileText(file);
            JsonNode jsonHomeWork = geminiAgent.generateQuestions(fileText);
    
            ArrayList<Question> questions = new ArrayList<Question>();
    
            for(JsonNode question : jsonHomeWork.get("questions")){
    
                Map<String, String> options = new HashMap<>();
                JsonNode optionsNode = question.get("options");
                
                Iterator<Map.Entry<String, JsonNode>> optionsFields = optionsNode.fields();
                
                while(optionsFields.hasNext()){
                    Map.Entry<String, JsonNode> field = optionsFields.next();
    
                    String letter = field.getKey();
                    String text = field.getValue().asText();
    
                    options.put(letter, text);
                }
    
                Question newQuestion = new Question(
                    question.get("summary").asText(),
                    question.get("number").asText(),
                    options,
                    question.get("correctAnswer").asText(),
                    question.get("explain").asText()
                );
                questions.add(newQuestion);
            }                    
            HomeWork hw = new HomeWork(jsonHomeWork.get("title").asText(), questions);
            System.out.println(hw);
            return hw;
        } catch (RuntimeException err){
            System.out.println(err);
        }
        throw new RuntimeException("Error generating homework");
    }

    public HomeWork createHomeWorkByText(){
        return null;
    }
}
