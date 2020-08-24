package com.resume.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
@Setter
@ToString
public class CurrentProfile extends User {
    private static final long serialVersionUID = 8682793918935353335L;

    private final Long id;
    private final String fullName;

    public CurrentProfile(Profile profile) {
        super(profile.getUid(), profile.getPassword(),
                true, true, true, true,
                Collections.singleton(new SimpleGrantedAuthority("USER")));
        this.id = profile.getId();
        this.fullName = profile.getFullName();
    }
}
