package com.resume.service;

import java.io.IOException;
import java.nio.file.Path;

public interface ImageResizer {
    void resize(Path sourceImageFile, Path destImageFile, int width, int height) throws IOException;
}
