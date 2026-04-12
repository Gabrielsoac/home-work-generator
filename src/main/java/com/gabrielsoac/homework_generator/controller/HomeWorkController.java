package com.gabrielsoac.homework_generator.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gabrielsoac.homework_generator.domain.HomeWorkContextData;
import com.gabrielsoac.homework_generator.domain.InputType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/homework/{inputType}")
public class HomeWorkController {
    
    @GetMapping()
    public ResponseEntity<String> generateHomeWork(@RequestParam InputType type, @RequestBody HomeWorkContextData payload) {
        return ResponseEntity.status(200).body("teste");
    }
    
}
