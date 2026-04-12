package com.gabrielsoac.homework_generator.domain;

import org.springframework.web.multipart.MultipartFile;

public record HomeWorkContextData (String text, MultipartFile file){}
