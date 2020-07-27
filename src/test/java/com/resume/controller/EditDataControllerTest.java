package com.resume.controller;


import com.resume.entity.ContactsProfile;
import com.resume.entity.Profile;
import com.resume.form.UploadImageResponse;
import com.resume.repository.ProfileRepository;
import com.resume.repository.SkillCategoryRepository;
import com.resume.service.HobbyService;
import com.resume.service.ImageService;
import com.resume.service.ImageType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig(EditDataControllerTest.Config.class)
public class EditDataControllerTest {

    @Configuration
    public static class Config {

    }

    @InjectMocks
    EditDataController editDataController;
    @Mock
    ImageService imageService;
    @Mock
    HobbyService hobbyService;
    @Mock
    ProfileRepository profileRepository;
    @Mock
    SkillCategoryRepository skillCategoryRepository;
    @Mock
    Profile profile;
    @Mock
    ContactsProfile contactsProfile;
    @Mock
    View mockView;

    MockMvc mockMvc;

    SimpleDateFormat dateFormat;
    MockMultipartFile profilePhotoMockMultipart;
    MockMultipartFile certificateFileMockMultipart;
    UploadImageResponse uploadImageResponse;

    @Autowired
    private WebApplicationContext webAppContext;

    @Before
    public void setUp() throws ParseException {
        //Инициализация Mock
        MockitoAnnotations.initMocks(this);
        //инициализация полей
        profilePhotoMockMultipart = new MockMultipartFile("profilePhoto", "test".getBytes());
        certificateFileMockMultipart = new MockMultipartFile("certificateFile", "test".getBytes());
        uploadImageResponse = new UploadImageResponse("Test_ImageName", "Test_00000000-0000-0000-0000-000000000000.jpg", "Test_00000000-0000-0000-0000-000000000000-sm.jpg");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        editDataController = new EditDataController(skillCategoryRepository, profileRepository, imageService, hobbyService);
        profile.setContactsProfile(contactsProfile);
        prepareMockitoObjects();
        //инициализация mockMvc
        this.mockMvc = MockMvcBuilders.standaloneSetup(editDataController).setSingleView(mockView).build();
    }

    private void prepareMockitoObjects() {
        //возвращаем Optional Profile
        Mockito.when(profileRepository.findById(13L)).thenReturn(Optional.of(profile));
        //возвращаем uploadImageResponse, когда используется NameService
        Mockito.when(imageService.processImage(profilePhotoMockMultipart, ImageType.AVATAR)).thenReturn(uploadImageResponse);
    }

    @Test
    public void givenWebAppContext_WhenInjected_ThenItShouldNotBeNull() {
        assertNotNull(webAppContext);
    }

    @Test
    public void getEditPage_whenRequestEdit_thenRetrievedAttributesIsCorrect() throws Exception {
        mockMvc.perform(get("/edit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("profileForm", Matchers.any(Profile.class)))
                .andExpect(view().name("edit/profile"));
    }

    @Test
    public void editProcess_whenPostEdit_thenBindAndProcess() throws Exception {
        MockMultipartHttpServletRequestBuilder profilePhotoMultipart = multipart("/edit")
                .file(profilePhotoMockMultipart);
        mockMvc.perform(profilePhotoMultipart)
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeHasErrors("profileForm"))
                .andExpect(view().name("edit/profile"));

//        Profile editProfile = prepareEditProfile(new Profile());
//        profilePhotoMultipart.flashAttr("profileForm", editProfile);
//        System.out.println(editProfile);
//        mockMvc.perform(profilePhotoMultipart)
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeHasNoErrors("profileForm"));
    }

    private Profile prepareEditProfile(Profile profile) throws ParseException {
        profile.setFirstName("Test_FirstName");
        profile.setLastName("Test_LastName");
        profile.setBirthDay(dateFormat.parse("1993-08-19"));
        profile.setCountry("Test_Country");
        profile.setCity("Test_City");
        profile.setObjective("Test_Objective");
        profile.setSummary("Test_Summary");
        profile.setLargePhoto("Test_00000000-0000-0000-0000-000000000000.jpg");
        ContactsProfile contactsProfile = new ContactsProfile();
        contactsProfile.setPhone("+77777777777");
        contactsProfile.setEmail("test@gmail.com");
        profile.setContactsProfile(contactsProfile);
        return profile;
    }

    @Test
    public void getEditContacts() {
    }

    @Test
    public void editContacts() {
    }

    @Test
    public void getEditSkills() {
    }

    @Test
    public void editSkills() {
    }

    @Test
    public void getEditPractics() {
    }

    @Test
    public void editPractics() {
    }

    @Test
    public void getEditCertificates() {
    }

    @Test
    public void editCertificates() {
    }

    @Test
    public void editCertificatesUpload() {
    }

    @Test
    public void getEditCourses() {
    }

    @Test
    public void editCourses() {
    }

    @Test
    public void getEditEducation() {
    }

    @Test
    public void editEducation() {
    }

    @Test
    public void getEditLanguages() {
    }

    @Test
    public void editLanguages() {
    }

    @Test
    public void getEditHobbies() {
    }

    @Test
    public void editHobbies() {
    }

    @Test
    public void getEditInfo() {
    }

    @Test
    public void editInfo() {
    }

    @Test
    public void getEditPassword() {
    }

    @Test
    public void editPassword() {
    }

    @Test
    public void getMyProfile() {
    }
}