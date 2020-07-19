package com.resume.entity;

import com.resume.annotation.constraints.EnglishLanguage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactsProfile {
    @Column(length = 20, unique = true)
    private String phone;
    @Column(length = 100, unique = true)
    @Email
    private String email;
    @Column(length = 60)
    @EnglishLanguage
    @Min(3)
    private String skype;
    @Column
    @URL
    private String vkontakte;
    @Column
    @URL
    private String facebook;
    @Column
    @URL
    private String linkedin;
    @Column
    @URL
    private String github;
    @Column
    @URL
    private String stackoverflow;
}
