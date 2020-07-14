package com.resume.controller;

import com.resume.entity.*;
import com.resume.form.*;
import com.resume.model.LanguageLevel;
import com.resume.model.LanguageType;
import com.resume.repository.ProfileRepository;
import com.resume.repository.SkillCategoryRepository;
import com.resume.service.HobbyService;
import com.resume.service.ImageService;
import com.resume.service.ImageType;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@Log4j
public class EditDataController {

    private SkillCategoryRepository skillCategoryRepository;
    private ProfileRepository profileRepository;
    private ImageService imageService;
    private HobbyService hobbyService;
    //TODO изменить id профиля на текущего пользователя
    private final static Long CURRENT_PROFILE_ID = 13L;

    @Autowired
    public EditDataController(SkillCategoryRepository skillCategoryRepository, ProfileRepository profileRepository, ImageService imageService, HobbyService hobbyService) {
        this.skillCategoryRepository = skillCategoryRepository;
        this.profileRepository = profileRepository;
        this.imageService = imageService;
        this.hobbyService = hobbyService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(LanguageType.class, LanguageType.getPropertyEditor());
        binder.registerCustomEditor(LanguageLevel.class, LanguageLevel.getPropertyEditor());
    }

    @GetMapping("/edit")
    public String getEditPage(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        byId.ifPresent(profile -> {
            model.addAttribute("profileForm", profile);
        });
        return "edit/profile";
    }

    @PostMapping("/edit")
    public String editProcess(@Valid @ModelAttribute("profileForm") Profile profileForm, BindingResult bindingResult, @RequestParam("profilePhoto") MultipartFile profilePhoto) {
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/profile";
        }
        if (!profilePhoto.isEmpty()) {
            UploadImageResponse uploadImageResponse = imageService.processImage(profilePhoto, ImageType.AVATAR);
            String largeUrl = uploadImageResponse.getLargeUrl();
            String smallUrl = uploadImageResponse.getSmallUrl();
            profileForm.setLargePhoto(largeUrl);
            profileForm.setSmallPhoto(smallUrl);
        }
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        if (byId.isPresent()) {
            Profile currentProfile = byId.get();
            if (profileForm.getLargePhoto() != null && profileForm.getSmallPhoto() != null) {
                currentProfile.setSmallPhoto(profileForm.getSmallPhoto());
                currentProfile.setLargePhoto(profileForm.getLargePhoto());
            }

            Date birthDayForm = profileForm.getBirthDay();
            if (checkNullAndEquals(birthDayForm, currentProfile.getBirthDay())) {
                currentProfile.setBirthDay(birthDayForm);
            }
            String countryForm = profileForm.getCountry();
            if (checkNullAndEquals(countryForm, currentProfile.getCountry())) {
                currentProfile.setCountry(countryForm);
            }
            String cityForm = profileForm.getCity();
            if (checkNullAndEquals(cityForm, currentProfile.getCity())) {
                currentProfile.setCity(cityForm);
            }
            String emailForm = profileForm.getContactsProfile().getEmail();
            if (checkNullAndEquals(emailForm, currentProfile.getContactsProfile().getEmail())) {
                currentProfile.getContactsProfile().setEmail(emailForm);
            }
            String phoneForm = profileForm.getContactsProfile().getPhone();
            if (checkNullAndEquals(phoneForm, currentProfile.getContactsProfile().getPhone())) {
                currentProfile.getContactsProfile().setPhone(phoneForm);
            }
            String objectiveForm = profileForm.getObjective();
            if (checkNullAndEquals(objectiveForm, currentProfile.getObjective())) {
                currentProfile.setObjective(objectiveForm);
            }
            String summaryForm = profileForm.getSummary();
            if (checkNullAndEquals(summaryForm, currentProfile.getSummary())) {
                currentProfile.setSummary(summaryForm);
            }
            profileRepository.save(currentProfile);
        }
        return "/edit/contacts";
    }

    public boolean checkNullAndEquals(Object formObj, Object currentProfileObj) {
        return formObj != null && !formObj.equals(currentProfileObj);
    }

    @GetMapping("/edit/contacts")
    public String getEditContacts(Model model) {
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            model.addAttribute("contactsForm", profile.getContactsProfile());
        });
        return "edit/contacts";
    }

    @PostMapping("/edit/contacts")
    public String editContacts(@Valid @ModelAttribute("contactsForm") ContactsProfile contactsForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/contacts";
        }
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setContactsProfile(contactsForm);
            profileRepository.save(profile);
        });
        return "edit/skills";
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
            debugBindingMessage(bindingResult);
            model.addAttribute("skillCategories", skillCategoryRepository.findAll());
            return "edit/skills";
        }
        List<Skill> skills = skillForm.getSkills();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setSkills(skills);
            profileRepository.save(profile);
        });
        return "redirect:/edit/practics";
    }

    @GetMapping("/edit/practics")
    public String getEditPractics(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        PracticsForm practicsForm = new PracticsForm();
        byId.ifPresent(profile -> {
            practicsForm.setPractics(profile.getPractics());
            model.addAttribute("profileId", profile.getId());
        });
        model.addAttribute("practicsForm", practicsForm);
        model.addAttribute("years", prepareYearsList());
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

    @PostMapping("/edit/practics")
    public String editPractics(@Valid @ModelAttribute("practicsForm") PracticsForm practicsForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/practics";
        }
        List<Practic> practics = practicsForm.getPractics();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setPractics(practics);
            profileRepository.save(profile);
        });
        return "redirect:/edit/certificates";
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
            debugBindingMessage(bindingResult);
            return "edit/certificates";
        }
        List<Certificate> certificates = certificateForm.getCertificates();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setCertificates(certificates);
            profileRepository.save(profile);
        });
        return "redirect:/edit/courses";
    }

    @PostMapping("/edit/certificates/upload")
    public @ResponseBody
    UploadImageResponse editCertificatesUpload(@RequestParam("certificateFile") MultipartFile certificateFile) {
        UploadImageResponse response = imageService.processImage(certificateFile, ImageType.CERTIFICATES);
        log.debug("New Certificate uploaded: " + response.getLargeUrl());
        return response;
    }

    @GetMapping("/edit/courses")
    public String getEditCourses(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        CourseForm courseForm = new CourseForm();
        byId.ifPresent(profile -> {
            courseForm.setCourses(profile.getCourses());
            model.addAttribute("profileId", profile.getId());
        });
        model.addAttribute("courseForm", courseForm);
        model.addAttribute("years", prepareYearsList());
        return "edit/courses";
    }

    @PostMapping("/edit/courses")
    public String editCourses(@Valid @ModelAttribute("courseFrom") CourseForm courseFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/courses";
        }
        List<Course> courses = courseFrom.getCourses();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setCourses(courses);
            profileRepository.save(profile);
        });
        return "redirect:/edit/education";
    }

    @GetMapping("/edit/education")
    public String getEditEducation(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        EducationForm educationForm = new EducationForm();
        byId.ifPresent(profile -> {
            educationForm.setEducations(profile.getEducations());
            model.addAttribute("profileId", profile.getId());
        });
        model.addAttribute("educationForm", educationForm);
        model.addAttribute("years", prepareYearsList());
        return "edit/education";
    }

    @PostMapping("/edit/education")
    public String editEducation(@Valid @ModelAttribute("educationForm") EducationForm educationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/education";
        }
        List<Education> educations = educationForm.getEducations();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setEducations(educations);
            profileRepository.save(profile);
        });
        return "redirect:/edit/languages";
    }

    @GetMapping("/edit/languages")
    public String getEditLanguages(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        LanguageForm languageForm = new LanguageForm();
        byId.ifPresent(profile -> {
            languageForm.setLanguages(profile.getLanguages());
            model.addAttribute("profileId", profile.getId());
        });
        model.addAttribute("languageForm", languageForm);
        model.addAttribute("languageTypes", EnumSet.allOf(LanguageType.class));
        model.addAttribute("languageLevels", EnumSet.allOf(LanguageLevel.class));
        return "edit/languages";
    }

    @PostMapping("/edit/languages")
    public String editLanguages(@Valid @ModelAttribute("languageForm") LanguageForm languageForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/languages";
        }
        List<Language> languages = languageForm.getLanguages();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setLanguages(languages);
            profileRepository.save(profile);
        });
        return "redirect:/edit/hobbies";
    }

    @GetMapping("/edit/hobbies")
    public String getEditHobbies(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        Set<Hobby> hobbies = new TreeSet<>();
        if (byId.isPresent()) {
            List<Hobby> hobbyList = byId.get().getHobbies();
            //log.debug("Hobbies list by profile: " + hobbyList);
            hobbies = hobbyService.getAllHobbiesListWithSelected(hobbyList);
        }
        int maxHobbies = 5;
        model.addAttribute("maxHobbies", maxHobbies);
        model.addAttribute("hobbies", hobbies);
        return "edit/hobbies";
    }

    @PostMapping("/edit/hobbies")
    public String editHobbies(@RequestParam("hobbies") List<String> hobbies) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        if (byId.isPresent()) {
            Profile profile = byId.get();
            List<Hobby> hobbiesByName = hobbyService.getHobbiesByName(hobbies, profile);
            //log.debug("hobbiesByName: " + hobbiesByName);
            profile.setHobbies(hobbiesByName);
            profileRepository.save(profile);
        }
        return "redirect:/edit/info";
    }

    @GetMapping("/edit/info")
    public String getEditInfo(Model model) {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        InfoForm infoForm = new InfoForm();
        byId.ifPresent(profile -> {
            infoForm.setInfo(profile.getInfo());
        });
        model.addAttribute("infoForm", infoForm);
        return "edit/info";
    }

    @PostMapping("/edit/info")
    public String editInfo(@Valid @ModelAttribute("infoForm") InfoForm infoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            debugBindingMessage(bindingResult);
            return "edit/info";
        }
        String info = infoForm.getInfo();
        profileRepository.findById(CURRENT_PROFILE_ID).ifPresent(profile -> {
            profile.setInfo(info);
            profileRepository.save(profile);
        });
        return "redirect:/my-profile";
    }

    @GetMapping("/edit/password")
    public String getEditPassword() {
        return "";
    }

    @PostMapping("/edit/password")
    public String editPassword() {
        return "";
    }

    @RequestMapping(value = "/my-profile")
    public String getMyProfile() {
        Optional<Profile> byId = profileRepository.findById(CURRENT_PROFILE_ID);
        if (byId.isPresent()) {
            Profile currentProfile = byId.get();
            return "redirect:/" + currentProfile.getUid();
        } else {
            return "redirect:/";
        }
    }

    private void debugBindingMessage(BindingResult bindingResult) {
        bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.getObjectName() + " | " + objectError.getDefaultMessage()));
    }

}
