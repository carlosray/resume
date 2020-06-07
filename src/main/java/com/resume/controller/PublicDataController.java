package com.resume.controller;

import com.resume.entity.Profile;
import com.resume.repository.ProfileRepository;
import com.resume.service.NameService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Log4j
@Controller
@Transactional
public class PublicDataController {

    private NameService nameService;
    private ProfileRepository profileRepository;

    @Autowired
    public PublicDataController(NameService nameService, ProfileRepository profileRepository) {
        this.nameService = nameService;
        this.profileRepository = profileRepository;
    }

    @RequestMapping("/{uid}")
    public String getProfile(@PathVariable String uid, Model model) {
        Profile byUid = profileRepository.findByUid(uid);
        if (byUid == null) {
            return "profile-not-found";
        }
        model.addAttribute("profile", byUid);
        return "profile";
    }

    @RequestMapping("/welcome")
    public String getWelcome(@Value("${profiles.perPage}") int profilesPerPage, Model model) {
        PageRequest pageRequest = PageRequest.of(0, profilesPerPage, Sort.by("id").descending());
        Page<Profile> profilePage = profileRepository.findAll(pageRequest);
        model.addAttribute("profilePage", profilePage);
        model.addAttribute("profiles", profilePage.getContent());
        return "welcome";
    }

    @GetMapping("/fragment/more")
    public String getMoreProfiles(@RequestParam("page") int page, @Value("${profiles.perPage}") int profilesPerPage, Model model) {
        PageRequest pageRequest = PageRequest.of(page, profilesPerPage, Sort.by("id").descending());
        Page<Profile> profilePage = profileRepository.findAll(pageRequest);
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
