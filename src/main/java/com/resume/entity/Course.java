package com.resume.entity;

import lombok.*;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(exclude = "profile")
@ToString(exclude = "profile")
@NoArgsConstructor
@Entity
public class Course extends BeginAndFinishDateModel implements Serializable {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
    @SequenceGenerator(name="course_generator", sequenceName = "course_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_generator")
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

    @Override
    public void setBeginDate(Date from) {

    }

    @PostLoad
    void postLoad() {
        if (this.finishDate != null) {
            LocalDate localFinishDate = convertToLocalDateViaInstant(this.finishDate);
            this.finishDateMonth = localFinishDate.getMonthValue();
            this.finishDateYear = localFinishDate.getYear();
        }
    }
}
