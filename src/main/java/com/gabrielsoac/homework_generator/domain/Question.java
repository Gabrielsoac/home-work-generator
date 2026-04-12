package com.gabrielsoac.homework_generator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Question {
    String number;
    String summary;
    String correctResponse;
    QuestionOptions options;
}
