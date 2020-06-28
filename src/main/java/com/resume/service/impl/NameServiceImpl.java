package com.resume.service.impl;

import com.resume.service.NameService;
import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Service;

@Service("nameService")
public class NameServiceImpl implements NameService {

    public String convertName(String name) {
        if (name.contains("-")) {
            String[] parts = name.split("-");
            return WordUtils.capitalize(parts[0]) + " " + WordUtils.capitalize(parts[1]);
        }
        return WordUtils.capitalize(name);
    }
}
