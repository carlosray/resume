package com.resume.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(exclude = "profile", callSuper = false)
@ToString(exclude = "profile")
@NoArgsConstructor
@Entity
public class Practic extends BeginAndFinishDateModel implements Serializable {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
    @SequenceGenerator(name="practic_generator", sequenceName = "practic_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="practic_generator")
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

    @PostLoad
    void postLoad() {
        if (this.beginDate != null) {
            LocalDate localBeginDate = convertToLocalDateViaInstant(this.beginDate);
            this.beginDateYear = localBeginDate.getYear();
            this.beginDateMonth = localBeginDate.getMonthValue();
        }
        if (this.finishDate != null) {
            LocalDate localFinishDate = convertToLocalDateViaInstant(this.finishDate);
            this.finishDateMonth = localFinishDate.getMonthValue();
            this.finishDateYear = localFinishDate.getYear();
        }
    }
}
