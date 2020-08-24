package com.resume.service.impl;

import com.resume.entity.*;
import com.resume.event.UpdateProfileEvent;
import com.resume.form.*;
import com.resume.repository.storage.ProfileRepository;
import com.resume.service.EditDataService;
import com.resume.service.HobbyService;
import com.resume.service.ImageService;
import com.resume.service.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class EditDataServiceImpl implements EditDataService {

    //repo
    private final ProfileRepository profileRepository;
    //services
    private final ImageService imageService;
    private final HobbyService hobbyService;
    private final ApplicationEventPublisher publisher;
    private final PasswordEncoder encoder;

    @Autowired
    public EditDataServiceImpl(ImageService imageService, ProfileRepository profileRepository, HobbyService hobbyService, ApplicationEventPublisher publisher, PasswordEncoder encoder) {
        this.imageService = imageService;
        this.profileRepository = profileRepository;
        this.hobbyService = hobbyService;
        this.publisher = publisher;
        this.encoder = encoder;
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
        registerEventForUpdateProfile(updatableProfile);
    }

    public void registerEventForUpdateProfile(Profile profile) {
        publisher.publishEvent(new UpdateProfileEvent(profile));
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

    @Override
    public void updatePassword(Profile updatableProfile, PasswordForm passwordForm) {
        String passwordEncoded = encoder.encode(passwordForm.getPassword());
        updatableProfile.setPassword(passwordEncoded);
        profileRepository.save(updatableProfile);
    }
}
