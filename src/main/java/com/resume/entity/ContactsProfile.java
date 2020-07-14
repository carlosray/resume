package com.resume.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactsProfile {
    @Column(length = 20, unique = true)
    private String phone;
    @Column(length = 100, unique = true)
    private String email;
    @Column(length = 60)
    private String skype;
    @Column
    private String vkontakte;
    @Column
    private String facebook;
    @Column
    private String linkedin;
    @Column
    private String github;
    @Column
    private String stackoverflow;
}
