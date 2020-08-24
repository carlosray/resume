package com.resume.controller;

import com.resume.entity.Profile;
import com.resume.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class PublicDataController {

    private SearchService searchService;

    @Value("${profiles.perPage}")
    private int profilesPerPage;

    @Autowired
    public PublicDataController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/{uid}")
    public String getProfile(@PathVariable String uid, Model model) {
        Profile byUid = searchService.findProfileByUid(uid);
        if (byUid == null) {
            return "profile-not-found";
        }
        model.addAttribute("profile", byUid);
        return "profile";
    } 

    @RequestMapping("/welcome")
    public String getWelcome(Model model) {
        PageRequest pageRequest = getPageRequestForProfiles(0);
        Page<Profile> profilePage = searchService.findAllProfilesPage(pageRequest);
        model.addAttribute("profilePage", profilePage);
        model.addAttribute("profiles", profilePage.getContent());
        return "welcome";
    }

    @GetMapping("/fragment/more")
    public String getMoreProfiles(@RequestParam("page") int page, Model model) {
        PageRequest pageRequest = getPageRequestForProfiles(page);
        Page<Profile> profilePage = searchService.findAllProfilesPage(pageRequest);
        model.addAttribute("profiles", profilePage.getContent());
        return "fragment/profile-items";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "query", required = false) String query, Model model) throws UnsupportedEncodingException {
        PageRequest pageRequest = getPageRequestForProfiles(0);
        if (StringUtils.isBlank(query)) {
            return "redirect:/welcome";
        }
        Page<Profile> profilePage = searchService.findBySearchQuery(query, pageRequest);
        model.addAttribute("profilePage", profilePage);
        model.addAttribute("profiles", profilePage.getContent());
        model.addAttribute("query", URLDecoder.decode(query, "UTF-8"));
        return "search-result";
    }

    private PageRequest getPageRequestForProfiles(int page) {
        return new PageRequest(page, profilesPerPage, new Sort(Sort.Direction.DESC, "id"));
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-in-failed")
    public String signInFailed(HttpSession session) {
        if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null) {
            return "redirect:/sign-in";
        }
        return "sign-in";
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
