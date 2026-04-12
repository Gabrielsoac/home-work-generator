package com.gabrielsoac.homework_generator.domain;

import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AnswerKey {
    private Map<Integer, String> answerKey;
    public void addAnswerKey(Integer questionKey, String answer){
        this.answerKey.put(questionKey, answer);
    }
}
