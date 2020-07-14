package com.resume.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadImageResponse implements Serializable {
    private static final long serialVersionUID = -6624015468229676660L;

    private String certificateName;
    private String largeUrl;
    private String smallUrl;
}
