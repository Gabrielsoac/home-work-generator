package com.gabrielsoac.homework_generator.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielsoac.homework_generator.interfaces.FileProcessor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Component
public class ProcessPDFFile implements FileProcessor {
    
    public String getFileText(MultipartFile file){
        try(
            InputStream inputStream = file.getInputStream(); 
            PDDocument document = PDDocument.load(inputStream);
        ){
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String text = pdfTextStripper.getText(document);
            return text;
            
        } catch (IOException exception){
            throw new RuntimeException("Error processing PDF file");
        }
    }
}
