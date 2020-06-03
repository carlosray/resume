package com.resume.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Practic implements Serializable {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;
    @Column(length = 100)
    private String position;
    @Column(length = 100)
    private String company;
    @Column(name = "begin_date")
    @Temporal(TemporalType.DATE)
    private Date beginDate;
    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @Column(length = 2147483647)
    private String responsibilities;
    @Column
    private String demo;
    @Column
    private String src;

}
