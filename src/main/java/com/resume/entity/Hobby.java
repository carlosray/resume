package com.resume.entity;

import lombok.*;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;

@Getter
@Setter
@EqualsAndHashCode(of = "name")
@ToString(exclude = "profile")
@NoArgsConstructor
@Entity
public class Hobby implements Serializable, Comparable<Hobby> {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
    @SequenceGenerator(name="hobby_generator", sequenceName = "hobby_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hobby_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    @Column(length = 30)
    private String name;

    @Transient
    private boolean selected;

    public Hobby(String name) {
        this.name = name;
    }

    public Hobby(String name, Profile profile) {
        this.name = name;
        this.profile = profile;
    }

    public String getCssClassName(){
        return name.replace(" ", "-").toLowerCase();
    }

    @Override
    public int compareTo(Hobby o) {
        return this.name.compareTo(o.name);
    }
}
