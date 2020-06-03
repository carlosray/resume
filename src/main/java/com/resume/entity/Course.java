package com.resume.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Course implements Serializable {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;
    @Column(length = 60)
    private String name;
    @Column(length = 60)
    private String school;
    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finishDate;

}
