package com.resume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resume.model.LanguageLevel;
import com.resume.model.LanguageType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(exclude = "profile")
@ToString(exclude = "profile")
@NoArgsConstructor
@Entity
public class Language implements Serializable {
    private static final long serialVersionUID = 3016136734521377289L;

    @Id
    @SequenceGenerator(name="language_generator", sequenceName = "language_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="language_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    @JsonIgnore
    private Profile profile;

    @Column(length = 30)
    private String name;

    @Column
    @Convert(converter = LanguageLevel.PersistJPAConverter.class)
    private LanguageLevel level;

    @Column
    @Convert(converter = LanguageType.PersistJPAConverter.class)
    private LanguageType type;

}
