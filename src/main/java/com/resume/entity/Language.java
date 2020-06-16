package com.resume.entity;

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
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
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
