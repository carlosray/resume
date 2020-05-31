package com.resume.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="skill_category")
public class SkillCategory implements Serializable {
    private static final long serialVersionUID = -4842918564474607837L;
    
    @Id
    @Column
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillCategory that = (SkillCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }

    @Override
    public String toString() {
        return "SkillCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
