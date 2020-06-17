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
@EqualsAndHashCode(exclude = "profile")
@ToString(exclude = "profile")
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
    @Transient
    private Integer beginDateMonth;
    @Transient
    private Integer beginDateYear;
    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @Transient
    private Integer finishDateMonth;
    @Transient
    private Integer finishDateYear;
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

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void setBeginDateMonth(Integer beginDateMonth) {
        this.beginDateMonth = beginDateMonth;
        setupBeginDate();
    }

    public void setBeginDateYear(Integer beginDateYear) {
        this.beginDateYear = beginDateYear;
        setupBeginDate();
    }

    private void setupBeginDate() {
        if (beginDateMonth != null && beginDateYear != null) {
            setBeginDate(Date.from
                    (LocalDate.of(beginDateYear, beginDateMonth, 1)
                    .atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()));
        }
        else {
            setBeginDate(null);
        }
    }

    public void setFinishDateMonth(Integer finishDateMonth) {
        this.finishDateMonth = finishDateMonth;
        setupFinishDate();
    }

    public void setFinishDateYear(Integer finishDateYear) {
        this.finishDateYear = finishDateYear;
        setupFinishDate();
    }

    private void setupFinishDate() {
        if (finishDateMonth != null && finishDateYear != null) {
            setFinishDate(Date.from
                    (LocalDate.of(finishDateYear, finishDateMonth, 1)
                            .atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()));
        }
        else {
            setFinishDate(null);
        }
    }
}
