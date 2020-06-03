package com.resume.entity;

import lombok.*;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name="skill_category")
public class SkillCategory implements Serializable {
    private static final long serialVersionUID = -4842918564474607837L;
    
    @Id
    @Column
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String category;
}
