package com.gabrielsoac.homework_generator.domain;

import java.util.Map;

import lombok.Getter;

@Getter
public class QuestionOptions {
    private Map<String, String> options;

    public QuestionOptions(Map<String, String> options){
        this.options = options;
    }
}
