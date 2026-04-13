package com.gabrielsoac.homework_generator.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class HomeWork {
    private String title;
    private Map<String, String> answerKey = new HashMap<String,String>();
    private ArrayList<Question> questions = new ArrayList<Question>();
    private Date createdAt = new Date();

    public HomeWork(String title, ArrayList<Question> questions){
        this.title = title;

        for(Question question : questions){
            addQuestion(question);
        }
    }

    private void addAnswerKey(String questionNumber, String answer){
        this.answerKey.put(questionNumber, answer);
    }

    private void addQuestion(Question question){
        this.questions.add(question);
        addAnswerKey(question.number(), question.correctAnswer());
    }
}
