package com.example.universityproject.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuestionDto {
    private MultipartFile file;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private int ans;
    private int chose;
}
