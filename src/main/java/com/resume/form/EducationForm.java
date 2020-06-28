package com.resume.form;

import com.resume.entity.Education;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EducationForm implements Serializable {
    private static final long serialVersionUID = 4213424069073122114L;

    List<Education> educations = new ArrayList<>();
}
