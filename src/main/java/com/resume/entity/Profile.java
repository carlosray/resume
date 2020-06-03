package com.resume.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"skills", "languages", "hobbies", "certificates", "courses", "practics", "educations"})
@EqualsAndHashCode
@Entity
@Table(name="profile")
public class Profile implements Serializable {
    private static final long serialVersionUID = 4902501196394768938L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private ProfileRestore profileRestore;
    @Column(unique = true)
    private String uid;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "birth_day")
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    @Column(length = 60)
    private String country;
    @Column(length = 100)
    private String city;
    @Column(length = 2147483647)
    private String objective;
    @Column(length = 2147483647)
    private String summary;
    @Column(name = "large_photo")
    private String largePhoto;
    @Column(name = "small_photo")
    private String smallPhoto;
    @Column(length = 2147483647)
    private String info;
    @Column
    private String password;
    @Column
    private boolean completed;
    @Column(insertable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp created;
    @Embedded
    private ContactsProfile contactsProfile;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Skill> skills;
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Language> languages;
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Hobby> hobbies;
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Certificate> certificates;
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Course> courses;
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Practic> practics;
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Education> educations;

}
