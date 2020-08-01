package com.resume.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Profile")
public class ProfileNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -375768688081534664L;

    public ProfileNotFoundException() {
        super("Profile not found");
    }
}
