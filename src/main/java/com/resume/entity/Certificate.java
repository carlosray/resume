package com.resume.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Certificate implements Serializable {
    private static final long serialVersionUID = 1416530477224390885L;

    @Id
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
