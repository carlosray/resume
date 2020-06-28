package com.resume.controller;

import com.resume.entity.*;
import com.resume.form.*;
import com.resume.repository.ProfileRepository;
import com.resume.repository.SkillCategoryRepository;
import com.resume.service.ImageService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Log4j
public class EditDataController {

    private SkillCategoryRepository skillCategoryRepository;
    private ProfileRepository profileRepository;
    private ImageService imageService;
    //TODO изменить id профиля на текущего пользователя
    private final static Long CURRENT_PROFILE_ID = 13L;

    @Autowired
    public EditDataController(SkillCategoryRepository skillCategoryRepository, ProfileRepository profileRepository, ImageService imageService) {
        this.skillCategoryRepository = skillCategoryRepository;
        this.profileRepository = profileRepository;
        this.imageService = imageService;
    }

    @GetMapping("/my-profile")
    public String getEditMyProfile() {
        return "myProfile";
    }

    @GetMapping("/edit")
    public String getEditPage() {
        return "edit";
    }

    @PostMapping("/edit")
    public String editProcess() {
        return "";
    }

    @GetMapping("/edit/contacts")
    public String getEditContacts() {
        return "";
    }

    @PostMapping("/edit/contacts")
    public String editContacts() {
        return "";
    }

    @GetMapping("/edit/skills")
    public String getEditSkills(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        SkillForm skillForm = new SkillForm();
        byId.ifPresent(profile -> {
            skillForm.setSkills(profile.getSkills());
        });
        model.addAttribute("skillForm", skillForm);
        model.addAttribute("skillCategories", skillCategoryRepository.findAll());
        return "edit/skills";
    }

    @PostMapping("/edit/skills")
    public String editSkills(@Valid @ModelAttribute("skillForm") SkillForm skillForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.getObjectName() + " | " + objectError.getDefaultMessage()));
            model.addAttribute("skillCategories", skillCategoryRepository.findAll());
            return "edit/skills";
        }
        List<Skill> skills = skillForm.getSkills();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setSkills(skills);
            profileRepository.save(profile);
        });
        return "redirect:/";
    }

    @GetMapping("/edit/practics")
    public String getEditPractics(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        PracticsForm practicsForm = new PracticsForm();
        byId.ifPresent(profile -> {
            practicsForm.setPractics(profile.getPractics());
        });
        model.addAttribute("practicsForm", practicsForm);
        model.addAttribute("years", prepareYearsList());
        return "edit/practics";
    }

    private List<Integer> prepareYearsList() {
        Year beginYear = Year.of(2000);
        Year endYear = Year.now();
        List<Integer> years = new ArrayList<>();
        while (beginYear.isBefore(endYear) || beginYear.equals(endYear)) {
            years.add(beginYear.getValue());
            beginYear = beginYear.plusYears(1);
        }
        return years;
    }

    @PostMapping("/edit/practics")
    public String editPractics(@Valid @ModelAttribute("practicsForm") PracticsForm practicsForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.getObjectName() + " | " + objectError.getDefaultMessage()));
            return "edit/practics";
        }
        List<Practic> practics = practicsForm.getPractics();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setPractics(practics);
            profileRepository.save(profile);
        });
        return "redirect:/";
    }

    @GetMapping("/edit/certificates")
    public String getEditCertificates(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        CertificateForm certificateForm = new CertificateForm();
        byId.ifPresent(profile -> {
            certificateForm.setCertificates(profile.getCertificates());
            model.addAttribute("profileId", profile.getId());
        });
        model.addAttribute("certificateForm", certificateForm);
        return "edit/certificates";
    }

    @PostMapping("/edit/certificates")
    public String editCertificates(@Valid @ModelAttribute("certificateForm") CertificateForm certificateForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.getObjectName() + " | " + objectError.getDefaultMessage()));
            return "edit/certificates";
        }
        List<Certificate> certificates = certificateForm.getCertificates();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setCertificates(certificates);
            profileRepository.save(profile);
        });
        return "redirect:/";
    }

    @PostMapping("/edit/certificates/upload")
    public @ResponseBody UploadCertificateResponse editCertificatesUpload(@RequestParam("certificateFile") MultipartFile certificateFile) {
        UploadCertificateResponse response = imageService.processCertificate(certificateFile);
        log.debug("New Certificate uploaded: " + response.getLargeUrl());
        return response;
    }

    @GetMapping("/edit/courses")
    public String getEditCourses(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        CourseForm courseForm = new CourseForm();
        byId.ifPresent(profile -> courseForm.setCourses(profile.getCourses()));
        model.addAttribute("courseForm", courseForm);
        model.addAttribute("years", prepareYearsList());
        return "edit/courses";
    }

    @PostMapping("/edit/courses")
    public String editCourses(@Valid @ModelAttribute("courseFrom") CourseForm courseFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.getObjectName() + " | " + objectError.getDefaultMessage()));
            return "edit/courses";
        }
        List<Course> courses = courseFrom.getCourses();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setCourses(courses);
            profileRepository.save(profile);
        });
        return "redirect:/";
    }

    @GetMapping("/edit/education")
    public String getEditEducation() {
        return "";
    }

    @PostMapping("/edit/education")
    public String editEducation() {
        return "";
    }

    @GetMapping("/edit/languages")
    public String getEditLanguages() {
        return "";
    }

    @PostMapping("/edit/languages")
    public String editLanguages() {
        return "";
    }

    @GetMapping("/edit/hobbies")
    public String getEditHobbies() {
        return "";
    }

    @PostMapping("/edit/hobbies")
    public String editHobbies() {
        return "";
    }

    @GetMapping("/edit/info")
    public String getEditInfo() {
        return "";
    }

    @PostMapping("/edit/info")
    public String editInfo() {
        return "";
    }

    @GetMapping("/edit/password")
    public String getEditPassword() {
        return "";
    }

    @PostMapping("/edit/password")
    public String editPassword() {
        return "";
    }
}
