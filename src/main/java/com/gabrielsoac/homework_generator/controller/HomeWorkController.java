package com.gabrielsoac.homework_generator.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gabrielsoac.homework_generator.domain.HomeWork;
import com.gabrielsoac.homework_generator.domain.InputType;
import com.gabrielsoac.homework_generator.domain.Question;
import com.gabrielsoac.homework_generator.service.HomeWorkService;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/homework")
public class HomeWorkController {
    @Autowired
    private HomeWorkService homeWorkService;
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StreamingResponseBody> generateHomeWork(
        @RequestParam() InputType inputType,
        @RequestParam(required = false) MultipartFile file,
        @RequestParam(required = false) String text) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, JsonMappingException, JsonProcessingException {

        try {
            if(InputType.file.equals(inputType)){
                HomeWork homeWork = homeWorkService.createHomeWorkByFile(file);
                StreamingResponseBody stream = outputstream -> {
                    Document document = new Document();
                    PdfWriter.getInstance(document, outputstream);

                    document.open();
                    document.add(new Paragraph(homeWork.getTitle()));
                    for(Question question : homeWork.getQuestions()){
                        document.add(new Paragraph(question.number() + ") " + question.summary()));
                        for(Map.Entry<String, String> option : question.options().entrySet()){
                            document.add(new Phrase(option.getKey() + " - " + option.getValue()));
                        }
                    }
                    document.close();
                };
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(stream);
            }
            if(InputType.text.toString().equals(inputType)){
                return ResponseEntity.ok().body(null);
            }

            throw new RuntimeException("Erro interno");
            
        } catch (RuntimeException exception) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
