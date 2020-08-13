package com.resume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resume.annotation.constraints.Adulthood;
import com.resume.annotation.constraints.EnglishLanguage;
import com.resume.annotation.constraints.PasswordStrengthConstraint;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"skills", "languages", "hobbies", "certificates", "courses", "practics", "educations"})
@EqualsAndHashCode
@Entity
@Table(name="profile")
@Document(indexName = "profile")
public class Profile implements Serializable {
    private static final long serialVersionUID = 4902501196394768938L;

    @Id
    @SequenceGenerator(name="profile_generator", sequenceName = "profile_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="profile_generator")
    private Long id;

    @OneToOne(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private ProfileRestore profileRestore;

    @Column(unique = true)
    private String uid;

    @Column(name = "first_name", nullable = false, length = 50)
    @EnglishLanguage
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @EnglishLanguage
    private String lastName;

    @NotNull
    @Past
    @Column(name = "birth_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @Transient
    @Adulthood
    private Integer age;

    @Column(length = 60)
    @EnglishLanguage
    private String country;

    @Column(length = 100)
    @EnglishLanguage
    private String city;

    @Column(length = 2147483647)
    @EnglishLanguage
    private String objective;

    @Column(length = 2147483647)
    private String summary;

    @Column(name = "large_photo")
    @JsonIgnore
    private String largePhoto;

    @Column(name = "small_photo")
    private String smallPhoto;

    @Column(length = 2147483647)
    private String info;

    @Column
    @PasswordStrengthConstraint
    @JsonIgnore
    private String password;

    @Column
    @JsonIgnore
    private boolean completed;

    @Column(insertable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Timestamp created;

    @Embedded
    @JsonIgnore
    private ContactsProfile contactsProfile;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Skill> skills;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Language> languages;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Hobby> hobbies;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Course> courses;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Practic> practics;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @OrderBy("finishYear DESC, beginYear DESC, id DESC")
    @JsonIgnore
    private List<Education> educations;

    @PostLoad
    void postLoadAge() {
        LocalDate localBirthDate = convertToLocalDateViaInstant(this.birthDay);
        this.age = calculateAge(localBirthDate, LocalDate.now());
    }

    public Integer calculateAge(
            LocalDate birthDate,
            LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
        postLoadAge();
    }

    public String getProfilePhoto(){
        if(largePhoto != null) {
            return largePhoto;
        } else {
            return "/static/img/profile-placeholder.png";
        }
    }

    @Transient
    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
