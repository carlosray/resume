package com.resume.service.impl;

import com.resume.service.FileStorageService;
import com.resume.service.ImageType;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Log4j
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${media.storage.root.path}")
    private String rootPath;

    @Override
    public String createImageLink(String imageName, ImageType imageType) {
        return "/media/" + imageType.getName() + "/" + imageName;
    }

    @Override
    public void save(String imageLink, Path tempImageFile) {
        Path destinationPath = Paths.get(rootPath + imageLink);
        try {
            Files.move(tempImageFile, destinationPath);
        } catch (IOException e) {
            throw new RuntimeException("Cant save file: " + e.getMessage(), e);
        }

    }

    public void remove(String... imageLinks) {
        for (String imageLink : imageLinks) {
            if (StringUtils.isNotBlank(imageLink)) {
                removeImageFile(Paths.get(rootPath + imageLink));
            }
        }
    }

    protected void removeImageFile(Path path) {
        try {
            if (Files.exists(path)) {
                Files.delete(path);
                log.debug("Image file + '" + path + "' removed successful");
            }
        } catch (IOException e) {
            log.error("Can't remove file: " + path, e);
        }
    }
}
