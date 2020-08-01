package com.resume.controller;

import com.resume.entity.*;
import com.resume.form.*;
import com.resume.model.LanguageLevel;
import com.resume.model.LanguageType;
import com.resume.repository.ProfileRepository;
import com.resume.repository.SkillCategoryRepository;
import com.resume.service.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.Year;
import java.util.*;
import java.util.stream.Stream;

@Controller
@Log4j
public class EditDataController {

    private final ImageService imageService;
    private final HobbyService hobbyService;
    private final EditDataService editDataService;

    @Autowired
    public EditDataController(ImageService imageService, HobbyService hobbyService, EditDataService editDataService) {
        this.imageService = imageService;
        this.hobbyService = hobbyService;
        this.editDataService = editDataService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(LanguageType.class, LanguageType.getPropertyEditor());
        binder.registerCustomEditor(LanguageLevel.class, LanguageLevel.getPropertyEditor());
    }

    @GetMapping("/edit")
    public String getEditPage(Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        model.addAttribute("profileForm", currentProfile);
        return "edit/profile";
    }

    @PostMapping("/edit")
    public String editProcess(@Valid @ModelAttribute("profileForm") Profile profileForm, BindingResult bindingResult, @RequestParam("profilePhoto") MultipartFile profilePhoto) {
        Profile currentProfile = editDataService.getCurrentProfile();
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/profile";
        }
        editDataService.updateProfile(currentProfile, profileForm, profilePhoto);
        return "redirect:/edit/contacts";
    }

    @GetMapping("/edit/contacts")
    public String getEditContacts(Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        model.addAttribute("contactsForm", currentProfile.getContactsProfile());
        return "edit/contacts";
    }

    @PostMapping("/edit/contacts")
    public String editContacts(@Valid @ModelAttribute("contactsForm") ContactsProfile contactsForm, BindingResult bindingResult, Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/contacts";
        }
        editDataService.updateContacts(currentProfile, contactsForm);
        return "redirect:edit/skills";
    }

    @GetMapping("/edit/skills")
    public String getEditSkills(Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        SkillForm skillForm = new SkillForm(currentProfile.getSkills());
        model.addAttribute("skillForm", skillForm);
        prepareSkillsPage(model);
        return "edit/skills";
    }

    private void prepareSkillsPage(Model model) {
        model.addAttribute("skillCategories", editDataService.getAllSkillCategories());
    }

    @PostMapping("/edit/skills")
    public String editSkills(@Valid @ModelAttribute("skillForm") SkillForm skillForm, BindingResult bindingResult, Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            prepareSkillsPage(model);
            return "edit/skills";
        }
        editDataService.updateSkills(currentProfile, skillForm);
        return "redirect:/edit/practics";
    }

    @GetMapping("/edit/practics")
    public String getEditPractics(Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        PracticsForm practicsForm = new PracticsForm(currentProfile.getPractics());
        preparePracticsPage(model, currentProfile);
        model.addAttribute("practicsForm", practicsForm);
        return "edit/practics";
    }

    private List<Integer> prepareYearsList() {
        Year beginYear = Year.of(1990);
        Year endYear = Year.now();
        List<Integer> years = new ArrayList<>();
        while (beginYear.isBefore(endYear) || beginYear.equals(endYear)) {
            years.add(beginYear.getValue());
            beginYear = beginYear.plusYears(1);
        }
        return years;
    }

    private void preparePracticsPage(Model model, Profile profile) {
        model.addAttribute("profileId", profile.getId());
        model.addAttribute("years", prepareYearsList());
    }

    @PostMapping("/edit/practics")
    public String editPractics(@Valid @ModelAttribute("practicsForm") PracticsForm practicsForm, BindingResult bindingResult, Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            preparePracticsPage(model, currentProfile);
            return "edit/practics";
        }
        editDataService.updatePractics(currentProfile, practicsForm);
        return "redirect:/edit/certificates";
    }

    @GetMapping("/edit/certificates")
    public String getEditCertificates(Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        CertificateForm certificateForm = new CertificateForm(currentProfile.getCertificates());
        prepareCertificatePage(model, currentProfile);
        model.addAttribute("certificateForm", certificateForm);
        return "edit/certificates";
    }

    private void prepareCertificatePage(Model model, Profile profile) {
        model.addAttribute("profileId", profile.getId());
    }

    @PostMapping("/edit/certificates")
    public String editCertificates(@Valid @ModelAttribute("certificateForm") CertificateForm certificateForm, BindingResult bindingResult, Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            prepareCertificatePage(model, currentProfile);
            return "edit/certificates";
        }
        editDataService.updateCertificates(currentProfile, certificateForm);
        return "redirect:/edit/courses";
    }

    @PostMapping("/edit/certificates/upload")
    public @ResponseBody
    UploadImageResponse editCertificatesUpload(@RequestParam("certificateFile") MultipartFile certificateFile) {
        UploadImageResponse response = imageService.processImage(certificateFile, ImageType.CERTIFICATES);
        return response;
    }

    @GetMapping("/edit/courses")
    public String getEditCourses(Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        CourseForm courseForm = new CourseForm(currentProfile.getCourses());
        prepareCoursePage(model, currentProfile);
        model.addAttribute("courseForm", courseForm);
        return "edit/courses";
    }

    private void prepareCoursePage(Model model, Profile profile) {
        model.addAttribute("profileId", profile.getId());
        model.addAttribute("years", prepareYearsList());
    }

    @PostMapping("/edit/courses")
    public String editCourses(@Valid @ModelAttribute("courseFrom") CourseForm courseForm, BindingResult bindingResult, Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            prepareCoursePage(model, currentProfile);
            return "edit/courses";
        }
        editDataService.updateCourses(currentProfile, courseForm);
        return "redirect:/edit/education";
    }

    @GetMapping("/edit/education")
    public String getEditEducation(Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        EducationForm educationForm = new EducationForm(currentProfile.getEducations());
        prepareEducationPage(model, currentProfile);
        model.addAttribute("educationForm", educationForm);
        return "edit/education";
    }

    private void prepareEducationPage(Model model, Profile profile) {
        model.addAttribute("profileId", profile.getId());
        model.addAttribute("years", prepareYearsList());
    }

    @PostMapping("/edit/education")
    public String editEducation(@Valid @ModelAttribute("educationForm") EducationForm educationForm, BindingResult bindingResult, Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            prepareEducationPage(model, currentProfile);
            return "edit/education";
        }
        editDataService.updateEducation(currentProfile, educationForm);
        return "redirect:/edit/languages";
    }

    @GetMapping("/edit/languages")
    public String getEditLanguages(Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        LanguageForm languageForm = new LanguageForm(currentProfile.getLanguages());
        prepareLanguagesPage(model, currentProfile);
        model.addAttribute("languageForm", languageForm);
        return "edit/languages";
    }

    private void prepareLanguagesPage(Model model, Profile profile) {
        model.addAttribute("profileId", profile.getId());
        model.addAttribute("languageTypes", EnumSet.allOf(LanguageType.class));
        model.addAttribute("languageLevels", EnumSet.allOf(LanguageLevel.class));
    }

    @PostMapping("/edit/languages")
    public String editLanguages(@Valid @ModelAttribute("languageForm") LanguageForm languageForm, BindingResult bindingResult, Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            prepareLanguagesPage(model, currentProfile);
            return "edit/languages";
        }
        editDataService.updateLanguages(currentProfile, languageForm);
        return "redirect:/edit/hobbies";
    }

    @GetMapping("/edit/hobbies")
    public String getEditHobbies(@Value("${profiles.hobby.max}") int maxHobbies, Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        List<Hobby> hobbyList = currentProfile.getHobbies();
        Set<Hobby> hobbies = hobbyService.getAllHobbiesListWithSelected(hobbyList);
        model.addAttribute("maxHobbies", maxHobbies);
        model.addAttribute("hobbies", hobbies);
        return "edit/hobbies";
    }

    @PostMapping("/edit/hobbies")
    public String editHobbies(@RequestParam("hobbies") List<String> hobbies) {
        Profile currentProfile = editDataService.getCurrentProfile();
        editDataService.updateHobbies(currentProfile, hobbies);
        return "redirect:/edit/info";
    }

    @GetMapping("/edit/info")
    public String getEditInfo(Model model) {
        Profile currentProfile = editDataService.getCurrentProfile();
        InfoForm infoForm = new InfoForm(currentProfile.getInfo());
        model.addAttribute("infoForm", infoForm);
        return "edit/info";
    }

    @PostMapping("/edit/info")
    public String editInfo(@Valid @ModelAttribute("infoForm") InfoForm infoForm, BindingResult bindingResult) {
        Profile currentProfile = editDataService.getCurrentProfile();
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/info";
        }
        editDataService.updateInfo(currentProfile, infoForm);
        return "redirect:/my-profile";
    }

    @GetMapping("/edit/password")
    public String getEditPassword(Model model) {
        PasswordForm passwordForm = new PasswordForm();
        model.addAttribute("passwordForm", passwordForm);
        return "password";
    }

    @PostMapping("/edit/password")
    public String editPassword(@Valid @ModelAttribute("passwordForm") PasswordForm passwordForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "password";
        }
        //TODO password hashing and saving
        return "redirect:/my-profile";
    }

    @RequestMapping(value = "/my-profile")
    public String getMyProfile() {
        Profile currentProfile = editDataService.getCurrentProfile();
        return "redirect:/" + currentProfile.getUid();
    }

    private void debugBindingMessage(BindingResult bindingResult) {
        bindingResult.getAllErrors().forEach(objectError -> log.debug("BindingError: " + objectError.toString()));
    }

}
