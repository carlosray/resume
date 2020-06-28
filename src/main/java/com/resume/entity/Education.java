package com.resume.entity;

import lombok.*;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(exclude = "profile")
@ToString(exclude = "profile")
@NoArgsConstructor
@Entity
public class Education implements Serializable {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
    @SequenceGenerator(name="education_generator", sequenceName = "education_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="education_generator")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;
    @Column(length = 100)
    private String summary;
    @Column(name = "begin_year")
    private Integer beginYear;
    @Column(name = "finish_year")
    private Integer finishYear;
    @Column(length = 2147483647)
    private String university;
    @Column
    private String faculty;



}
