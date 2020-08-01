package com.resume.controller;

import com.resume.entity.Profile;
import com.resume.repository.ProfileRepository;
import com.resume.service.PublicDataService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
public class PublicDataController {

    private PublicDataService publicDataService;

    @Value("${profiles.perPage}")
    private int profilesPerPage;

    @Autowired
    public PublicDataController(PublicDataService publicDataService) {
        this.publicDataService = publicDataService;
    }

    @RequestMapping("/{uid}")
    public String getProfile(@PathVariable String uid, Model model) {
        Profile byUid = publicDataService.findProfileByUid(uid);
        if (byUid == null) {
            return "profile-not-found";
        }
        model.addAttribute("profile", byUid);
        return "profile";
    } 

    @RequestMapping("/welcome")
    public String getWelcome(Model model) {
        PageRequest pageRequest = PageRequest.of(0, profilesPerPage, Sort.by("id").descending());
        Page<Profile> profilePage = publicDataService.getProfilePage(pageRequest);
        model.addAttribute("profilePage", profilePage);
        model.addAttribute("profiles", profilePage.getContent());
        return "welcome";
    }

    @GetMapping("/fragment/more")
    public String getMoreProfiles(@RequestParam("page") int page, Model model) {
        PageRequest pageRequest = PageRequest.of(page, profilesPerPage, Sort.by("id").descending());
        Page<Profile> profilePage = publicDataService.getProfilePage(pageRequest);
        model.addAttribute("profiles", profilePage.getContent());
        return "fragment/profile-items";
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
