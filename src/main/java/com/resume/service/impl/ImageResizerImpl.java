package com.resume.service.impl;

import com.resume.service.ImageResizer;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class ImageResizerImpl implements ImageResizer {
    @Override
    public void resize(Path sourceImageFile, Path destImageFile, int width, int height) throws IOException {
        Thumbnails.of(sourceImageFile.toFile())
                .size(width, height)
                .toFile(destImageFile.toFile());
    }
}
