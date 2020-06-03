package com.resume.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profile_restore")
public class ProfileRestore implements Serializable {
    private static final long serialVersionUID = -6088653777866779639L;

    @Id
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Profile profile;
    @Column(nullable = false, unique = true, length = 100)
    private String token;
}
