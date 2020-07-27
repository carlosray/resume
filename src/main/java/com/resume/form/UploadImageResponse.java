package com.resume.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageResponse implements Serializable {
    private static final long serialVersionUID = -6624015468229676660L;

    private String imageName;
    private String largeUrl;
    private String smallUrl;
}
