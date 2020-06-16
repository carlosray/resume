package com.resume.controller;

import com.resume.entity.Profile;
import com.resume.entity.Skill;
import com.resume.form.SkillForm;
import com.resume.repository.ProfileRepository;
import com.resume.repository.SkillCategoryRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Controller
@Log4j
public class EditDataController {

    private SkillCategoryRepository skillCategoryRepository;
    private ProfileRepository profileRepository;

    @Autowired
    public EditDataController(SkillCategoryRepository skillCategoryRepository, ProfileRepository profileRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
        this.profileRepository = profileRepository;
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
        //TODO изменить id профиля на текущего пользователя
        Optional<Profile> byId = profileRepository.findById(49L);
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
        //TODO изменить id профиля на текущего пользователя
        profileRepository.findById(49L).ifPresent(profile -> {
            profile.setSkills(skills);
            profileRepository.save(profile);
        });
        return "redirect:/";
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
