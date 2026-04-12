package com.gabrielsoac.homework_generator.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielsoac.homework_generator.interfaces.FileProcessor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@AllArgsConstructor
@Getter
@Setter
public class ProcessDocxFile implements FileProcessor {
    
    public String getFileText(MultipartFile file) {
        try(
            InputStream inputStream = file.getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);
            XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
        ){
            return wordExtractor.getText();
        } catch (IOException exception){
            throw new RuntimeException("Error getting Docx text: " + exception.getMessage());
        }

    } 
}
