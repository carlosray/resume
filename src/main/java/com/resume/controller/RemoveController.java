package com.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/remove")
public class RemoveController {

    @GetMapping
    public String getRemove() {
       return "remove";
    }

    @PostMapping
    public String remove() {
        return "";
    }
}
