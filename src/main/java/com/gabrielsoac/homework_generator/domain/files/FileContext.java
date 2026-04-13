package com.gabrielsoac.homework_generator.domain.files;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileContext {
    private MultipartFile file;
    private FileType type;

    public FileContext(MultipartFile file){
        String filename = file.getOriginalFilename();
        int indexDot = filename.lastIndexOf('.');
        String extension = filename.substring(indexDot + 1);

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
