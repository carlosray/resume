package com.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController{

    @GetMapping("${application.error-path}")
    public String renderErrorPage() {
        return "errorPage";
    }

    //GET  /sign-in-failed
    @GetMapping("/sign-in-failed")
    public String signInFailed() {
        return "signInFailed";
    }

}
