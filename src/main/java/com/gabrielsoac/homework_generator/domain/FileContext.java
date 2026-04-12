package com.gabrielsoac.homework_generator.domain;

import java.io.File;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileContext {
    private File file;
    private FileType type;

    public FileContext(File file){
        String filename = file.getName();
        int indexDot = filename.lastIndexOf('.');
        String extension = filename.substring(indexDot);

        boolean valid = false;
        for(FileType type : FileType.values()){
            if(extension.toLowerCase().equals(type.toString().toLowerCase())) valid = true;
        }
        if(!valid){
            throw new RuntimeException("Error defining file, only files with type .docx or .pdf are allows");
        }
        this.file = file;
        this.type = FileType.valueOf(extension);
    }

    public String getFileName(){
        return this.file.getName();
    }
}
