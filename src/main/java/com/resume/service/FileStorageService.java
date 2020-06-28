package com.resume.service;

import java.nio.file.Path;

public interface FileStorageService {
    String createImageLink (String imageName, ImageType imageType);

    void save(String imageLink, Path tempImageFile);

    void remove (String ... imageLinks);

}
