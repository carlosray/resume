package com.resume.entity;

import lombok.*;
import lombok.extern.log4j.Log4j;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(exclude = "profile")
@ToString(exclude = "profile")
@NoArgsConstructor
@Entity
public class Certificate implements Serializable {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
    @SequenceGenerator(name="certificate_generator", sequenceName = "certificate_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="certificate_generator")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;
    @Column(length = 50)
    private String name;
    @Column(name = "large_url")
    private String largeUrl;
    @Column(name = "small_url")
    private String smallUrl;

}
