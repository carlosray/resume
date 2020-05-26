package com.resume.controller;

import com.resume.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicDataController {

    private NameService nameService;

    @Autowired
    public PublicDataController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/{uid}")
    public String getProfile(@PathVariable String uid, Model model) {
        String fullName = nameService.convertName(uid);
        model.addAttribute("fullName", fullName);
        return "profile";
    }

    @GetMapping("/welcome")
    public String getWelcome() {
        return "welcome";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "signIn";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/sign-up")
    public String processSignUp() {
        return "";
    }

    @GetMapping("/sign-up/success")
    public String signUpSuccess() {
        return "signUpSuccess";
    }

    @GetMapping("/restore")
    public String restore() {
        return "restore";
    }

    @PostMapping("/restore")
    public String restoreProcess() {
        return "";
    }

    @GetMapping("/restore/success")
    public String restoreSuccess() {
        return "restoreSuccess";
    }

    @GetMapping("/restore/{token}")
    public String getRestoreByToken(@PathVariable String token) {
        return "getRestoreByToken";
    }


}
