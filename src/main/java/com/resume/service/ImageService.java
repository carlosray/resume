package com.resume.service;

import com.resume.form.UploadCertificateResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    UploadCertificateResponse processCertificate(MultipartFile file);
}
