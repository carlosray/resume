package com.resume.service;

import com.resume.entity.CurrentProfile;
import com.resume.entity.Profile;

public interface SecurityService {
    Long getCurrentProfileId();
    CurrentProfile getCurrentAuthorizedProfile();
    void authenticate(Profile profile);
}
