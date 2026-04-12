package com.gabrielsoac.homework_generator.domain;

import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;

@Getter
public class HomeWork {
    private String title;
    private AnswerKey answerKey;
    private ArrayList<Question> questions = new ArrayList<Question>();
    private Date createdAt = new Date();

    public HomeWork(String title, AnswerKey answerKey){
        this.title = title;
        this.answerKey = answerKey;
    }
}
