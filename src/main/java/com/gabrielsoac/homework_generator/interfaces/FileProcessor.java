package com.gabrielsoac.homework_generator.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileProcessor {
    String getFileText(MultipartFile file);   
}
