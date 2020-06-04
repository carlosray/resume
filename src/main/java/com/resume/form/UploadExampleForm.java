package com.resume.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadExampleForm {
    private String name;
    private MultipartFile multipartFile;
}
