package com.gabrielsoac.homework_generator.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielsoac.homework_generator.domain.InputType;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/homework")
public class HomeWorkController {
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> generateHomeWork(
        @RequestParam() InputType type,
        @RequestParam(required = false) MultipartFile file,
        @RequestParam(required = false) String text) {

        if(file.isEmpty() && text.isBlank()) throw new RuntimeException("Context Data is Required, please, enter the file or text");
        return ResponseEntity.status(200).body(type.toString());
    }
}
