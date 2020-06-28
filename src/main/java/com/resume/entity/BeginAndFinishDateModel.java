package com.resume.entity;

import lombok.Data;

import javax.persistence.Transient;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
public abstract class BeginAndFinishDateModel {
    @Transient
    protected Integer beginDateMonth;
    @Transient
    protected Integer beginDateYear;
    @Transient
    protected Integer finishDateMonth;
    @Transient
    protected Integer finishDateYear;


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

    protected void setupBeginDate() {
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

    protected void setupFinishDate() {
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

    public abstract void setBeginDate(Date from);

    public abstract void setFinishDate(Date from);
}
