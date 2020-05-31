package com.resume.controller;

import com.resume.entity.SkillCategory;
import com.resume.repository.SkillCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Collections;

@Controller
public class EditDataController {

    private SkillCategoryRepository skillCategoryRepository;

    @Autowired
    public EditDataController(SkillCategoryRepository skillCategoryRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
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
        Iterable<SkillCategory> all = skillCategoryRepository.findAll();
        model.addAttribute("skillCategories", all);
        return "edit-skills";
    }

    @PostMapping("/edit/skills")
    public String editSkills() {
        return "";
    }

    @GetMapping("/edit/practics")
    public String getEditPractics() {
        return "";
    }

    @PostMapping("/edit/practics")
    public String editPractics() {
        return "";
    }

    @GetMapping("/edit/certificates")
    public String getEditCertificates() {
        return "";
    }

    @PostMapping("/edit/certificates")
    public String editCertificates() {
        return "";
    }

    @PostMapping("/edit/certificates/upload")
    public String editCertificatesUpload() {
        return "";
    }

    @GetMapping("/edit/courses")
    public String getEditCourses() {
        return "";
    }

    @PostMapping("/edit/courses")
    public String editCourses() {
        return "";
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
