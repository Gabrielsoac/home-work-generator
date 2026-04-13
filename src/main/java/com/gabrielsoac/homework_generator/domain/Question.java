package com.gabrielsoac.homework_generator.domain;

import java.util.Map;

public record Question 
    (
        String summary,
        String number,
        Map<String,String> options,
        String correctAnswer,
        String Explan
    ){}
