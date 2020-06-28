package com.resume.form;

import com.resume.entity.Course;
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
public class CourseForm implements Serializable {
    private static final long serialVersionUID = -484530273741077791L;

    List<Course> courses = new ArrayList<>();
}
