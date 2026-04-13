package com.gabrielsoac.homework_generator.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gabrielsoac.homework_generator.domain.HomeWork;
import com.gabrielsoac.homework_generator.domain.InputType;
import com.gabrielsoac.homework_generator.service.HomeWorkService;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<HomeWork> generateHomeWork(
        @RequestParam() InputType inputType,
        @RequestParam(required = false) MultipartFile file,
        @RequestParam(required = false) String text) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, JsonMappingException, JsonProcessingException {

        try {
            if(InputType.file.equals(inputType)){
                HomeWork homeWork = homeWorkService.createHomeWorkByFile(file);
                return ResponseEntity.ok().body(homeWork);
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
