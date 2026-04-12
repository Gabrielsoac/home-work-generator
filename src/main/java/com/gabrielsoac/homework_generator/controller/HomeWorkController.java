package com.gabrielsoac.homework_generator.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gabrielsoac.homework_generator.domain.FileContext;
import com.gabrielsoac.homework_generator.domain.FileType;
import com.gabrielsoac.homework_generator.domain.GeminiAIAgent;
import com.gabrielsoac.homework_generator.domain.InputType;
import com.gabrielsoac.homework_generator.interfaces.FileProcessor;
import com.gabrielsoac.homework_generator.service.ProcessDocxFile;
import com.gabrielsoac.homework_generator.service.ProcessPDFFile;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/homework")
public class HomeWorkController {

    FileProcessor pdfFileProcessor = new ProcessPDFFile();
    FileProcessor docxFileProcessor = new ProcessDocxFile();
    GeminiAIAgent geminiAgent = new GeminiAIAgent();

    Map<FileType, FileProcessor> fileProcessors = 
        new HashMap<
            FileType,
            FileProcessor
        >(Map.of(FileType.pdf, pdfFileProcessor, FileType.docx, docxFileProcessor));
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> generateHomeWork(
        @RequestParam() InputType inputType,
        @RequestParam(required = false) MultipartFile file,
        @RequestParam(required = false) String text) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, JsonMappingException, JsonProcessingException {

        try {
            if(file.isEmpty() && text.isBlank()) throw new RuntimeException("Context Data is required, please, enter the file or text");
            if(InputType.file.equals(inputType)){
                FileContext fileContext = new FileContext(file);
                String fileText = fileProcessors.get(fileContext.getType()).getFileText(file);
                geminiAgent.generateHomeWork(fileText);
                return ResponseEntity.ok().body(fileText);
            }
            if(InputType.text.toString().equals(inputType)){
                return ResponseEntity.ok().body("texto aqui");
            }

            throw new RuntimeException("Erro interno");
            
        } catch (RuntimeException exception) {
            return ResponseEntity.status(500).body(exception.getMessage());
        }
    }
}
