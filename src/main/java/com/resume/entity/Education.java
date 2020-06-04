package com.resume.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Education implements Serializable {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
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
