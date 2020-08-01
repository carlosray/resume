package com.resume.service.impl;

import com.resume.entity.*;
import com.resume.exception.ProfileNotFoundException;
import com.resume.form.*;
import com.resume.repository.ProfileRepository;
import com.resume.repository.SkillCategoryRepository;
import com.resume.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EditDataServiceImpl implements EditDataService {

    //repo
    private final ProfileRepository profileRepository;
    private final SkillCategoryRepository skillCategoryRepository;
    //services
    private final ImageService imageService;
    private final SecurityService securityService;
    private final HobbyService hobbyService;

    @Autowired
    public EditDataServiceImpl(ImageService imageService, ProfileRepository profileRepository, SkillCategoryRepository skillCategoryRepository, SecurityService securityService, HobbyService hobbyService) {
        this.imageService = imageService;
        this.profileRepository = profileRepository;
        this.skillCategoryRepository = skillCategoryRepository;
        this.securityService = securityService;
        this.hobbyService = hobbyService;
    }

    @Override
    @Transactional
    public Profile getCurrentProfile() throws ProfileNotFoundException {
        Long currentProfileId = securityService.getCurrentProfileId();
        Optional<Profile> byId = profileRepository.findById(currentProfileId);
        if (!byId.isPresent()) throw new ProfileNotFoundException();
        return byId.get();
    }

    @Override
    @Transactional
    public void updateProfile(Profile updatableProfile, Profile profileForm) {
        updateProfile(updatableProfile, profileForm, null);
    }

    @Override
    @Transactional
    public void updateProfile(Profile updatableProfile, Profile profileForm, MultipartFile profilePhoto) {
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            UploadImageResponse uploadImageResponse = imageService.processImage(profilePhoto, ImageType.AVATAR);
            String largeUrl = uploadImageResponse.getLargeUrl();
            String smallUrl = uploadImageResponse.getSmallUrl();
            profileForm.setLargePhoto(largeUrl);
            profileForm.setSmallPhoto(smallUrl);
        }

        if (profileForm.getLargePhoto() != null && profileForm.getSmallPhoto() != null) {
            updatableProfile.setSmallPhoto(profileForm.getSmallPhoto());
            updatableProfile.setLargePhoto(profileForm.getLargePhoto());
        }

        Date birthDayForm = profileForm.getBirthDay();
        if (notNullAndNotEquals(birthDayForm, updatableProfile.getBirthDay())) {
            updatableProfile.setBirthDay(birthDayForm);
        }
        String countryForm = profileForm.getCountry();
        if (notNullAndNotEquals(countryForm, updatableProfile.getCountry())) {
            updatableProfile.setCountry(countryForm);
        }
        String cityForm = profileForm.getCity();
        if (notNullAndNotEquals(cityForm, updatableProfile.getCity())) {
            updatableProfile.setCity(cityForm);
        }
        String emailForm = profileForm.getContactsProfile().getEmail();
        if (notNullAndNotEquals(emailForm, updatableProfile.getContactsProfile().getEmail())) {
            updatableProfile.getContactsProfile().setEmail(emailForm);
        }
        String phoneForm = profileForm.getContactsProfile().getPhone();
        if (notNullAndNotEquals(phoneForm, updatableProfile.getContactsProfile().getPhone())) {
            updatableProfile.getContactsProfile().setPhone(phoneForm);
        }
        String objectiveForm = profileForm.getObjective();
        if (notNullAndNotEquals(objectiveForm, updatableProfile.getObjective())) {
            updatableProfile.setObjective(objectiveForm);
        }
        String summaryForm = profileForm.getSummary();
        if (notNullAndNotEquals(summaryForm, updatableProfile.getSummary())) {
            updatableProfile.setSummary(summaryForm);
        }
        profileRepository.save(updatableProfile);
    }

    private boolean notNullAndNotEquals(Object formObj, Object currentProfileObj) {
        return formObj != null && !formObj.equals(currentProfileObj);
    }

    @Override
    @Transactional
    public void updateContacts(Profile updatableProfile, ContactsProfile contactsForm) {
        updatableProfile.setContactsProfile(contactsForm);
        profileRepository.save(updatableProfile);
    }

    @Override
    @Transactional
    public Iterable<SkillCategory> getAllSkillCategories() {
        return skillCategoryRepository.findAll();
    }

    @Override
    @Transactional
    public void updateSkills(Profile updatableProfile, SkillForm skillForm) {
        List<Skill> skills = skillForm.getSkills();
        updatableProfile.setSkills(skills);
        profileRepository.save(updatableProfile);
    }

    @Override
    public void updatePractics(Profile updatableProfile, PracticsForm practicsForm) {
        List<Practic> practics = practicsForm.getPractics();
        updatableProfile.setPractics(practics);
        profileRepository.save(updatableProfile);
    }

    @Override
    public void updateLanguages(Profile updatableProfile, LanguageForm languageForm) {
        List<Language> languages = languageForm.getLanguages();
        updatableProfile.setLanguages(languages);
        profileRepository.save(updatableProfile);
    }

    @Override
    public void updateCertificates(Profile updatableProfile, CertificateForm certificateForm) {
        List<Certificate> certificates = certificateForm.getCertificates();
        updatableProfile.setCertificates(certificates);
        profileRepository.save(updatableProfile);
    }

    @Override
    public void updateCourses(Profile updatableProfile, CourseForm courseForm) {
        List<Course> courses = courseForm.getCourses();
        updatableProfile.setCourses(courses);
        profileRepository.save(updatableProfile);
    }

    @Override
    public void updateHobbies(Profile updatableProfile, List<String> hobbies) {
        List<Hobby> hobbiesByName = hobbyService.getHobbiesByName(hobbies, updatableProfile);
        updatableProfile.setHobbies(hobbiesByName);
        profileRepository.save(updatableProfile);
    }

    @Override
    public void updateEducation(Profile updatableProfile, EducationForm educationForm) {
        List<Education> educations = educationForm.getEducations();
        updatableProfile.setEducations(educations);
        profileRepository.save(updatableProfile);
    }

    @Override
    public void updateInfo(Profile updatableProfile, InfoForm infoForm) {
        String info = infoForm.getInfo();
        updatableProfile.setInfo(info);
        profileRepository.save(updatableProfile);

    }
}
