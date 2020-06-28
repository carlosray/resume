package com.resume.service.impl;

import com.resume.form.UploadCertificateResponse;
import com.resume.service.FileStorageService;
import com.resume.service.ImageResizer;
import com.resume.service.ImageService;
import com.resume.service.ImageType;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@Log4j
public class ImageServiceImpl implements ImageService {

    private FileStorageService fileStorageService;
    private ImageResizer imageResizer;

    @Autowired
    public ImageServiceImpl(FileStorageService fileStorageService, ImageResizer imageResizer) {
        this.fileStorageService = fileStorageService;
        this.imageResizer = imageResizer;
    }

    @Override
    public UploadCertificateResponse processCertificate(MultipartFile file) {
        UploadCertificateResponse response = new UploadCertificateResponse();
        try {
            String certificateName = getImageName(file.getOriginalFilename());
            String largeName = generateNewFileName();
            String smallName = getSmallImageName(largeName);
            Path largeTmp = Files.createTempFile("large", ".jpg");
            Path smallTmp = Files.createTempFile("small", ".jpg");
            transferUploadToFile(file, largeTmp);
            resizeUpload(largeTmp, smallTmp, ImageType.CERTIFICATES);
            //TODO ThreadLocal
            String largeLink = fileStorageService.createImageLink(largeName, ImageType.CERTIFICATES);
            String smallLink = fileStorageService.createImageLink(smallName, ImageType.CERTIFICATES);
            fileStorageService.save(largeLink, largeTmp);
            fileStorageService.save(smallLink, smallTmp);

            response.setLargeUrl(largeLink);
            response.setSmallUrl(smallLink);
            response.setCertificateName(certificateName);
        } catch (Exception e) {
            throw new RuntimeException("Can`t process certificate: " + e.getMessage(), e);
        }
        return response;
    }

    protected String generateNewFileName() {
        return UUID.randomUUID().toString() + ".jpg";
    }

    protected String getSmallImageName(String largePhoto) {
        return largePhoto.replace(".jpg", "-sm.jpg");
    }

    protected void transferUploadToFile(MultipartFile uploadPhoto, Path destPath) throws IOException {
        String contentType = uploadPhoto.getContentType();
        log.debug("Content type for upload: " + contentType);
        if (!contentType.contains("png") && !contentType.contains("jpg") && !contentType.contains("jpeg")) {
            throw new RuntimeException("Only png and jpg image formats are supported: Current content type=" + contentType);
        }
        uploadPhoto.transferTo(destPath.toFile());
    }

    protected void resizeUpload(Path largeTmp, Path smallTmp, ImageType imageType) throws IOException {
        imageResizer.resize(largeTmp, smallTmp, imageType.getSmallWidth(), imageType.getSmallHeight());
        imageResizer.resize(largeTmp, largeTmp, imageType.getLargeWidth(), imageType.getLargeHeight());
    }

    private String getImageName(String fileName) {
        if (fileName == null) {
            return "";
        }
        int point = fileName.lastIndexOf('.');
        if (point != -1) {
            fileName = fileName.substring(0, point);
        }
        return WordUtils.capitalize(fileName);
    }

}
