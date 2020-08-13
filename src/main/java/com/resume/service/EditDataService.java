package com.resume.service;

import com.resume.entity.ContactsProfile;
import com.resume.entity.Profile;
import com.resume.form.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EditDataService {
    void updateProfile(Profile updatableProfile, Profile profileForm);
    void updateProfile(Profile updatableProfile, Profile profileForm, MultipartFile profilePhoto);
    void updateContacts(Profile updatableProfile, ContactsProfile contactsForm);
    void updateSkills(Profile updatableProfile, SkillForm skillForm);
    void updateLanguages(Profile updatableProfile, LanguageForm languageForm);
    void updateCertificates(Profile updatableProfile, CertificateForm certificateForm);
    void updateCourses(Profile updatableProfile, CourseForm courseForm);
    void updateHobbies(Profile updatableProfile, List<String> hobbies);
    void updateEducation(Profile updatableProfile, EducationForm educationForm);
    void updatePractics(Profile updatableProfile, PracticsForm practicsForm);
    void updateInfo(Profile updatableProfile, InfoForm infoForm);
}
