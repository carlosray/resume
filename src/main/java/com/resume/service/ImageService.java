package com.resume.service;

import com.resume.form.UploadImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    UploadImageResponse processImage(MultipartFile file, ImageType imageType);
}
