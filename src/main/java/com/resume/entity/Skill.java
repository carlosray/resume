package com.resume.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(exclude = "profile")
@NoArgsConstructor
@ToString
@Entity
public class Skill implements Serializable {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
    @SequenceGenerator(name="skill_generator", sequenceName = "skill_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill_generator")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;
    @Column(length = 50)
    private String category;
    @Column(length = 2147483647)
    private String value;
}
