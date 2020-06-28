package com.resume.service;

import lombok.Getter;

@Getter
public enum ImageType {
    AVATAR(110, 110, 400, 400),
    CERTIFICATES(142, 100, 900, 400);

    private final int smallWidth;
    private final int smallHeight;
    private final int largeWidth;
    private final int largeHeight;

    ImageType(int smallWidth, int smallHeight, int largeWidth, int largeHeight) {
        this.smallWidth = smallWidth;
        this.smallHeight = smallHeight;
        this.largeWidth = largeWidth;
        this.largeHeight = largeHeight;
    }

    public String getName() {
        return name().toLowerCase();
    }
}
