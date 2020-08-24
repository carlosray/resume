package com.resume.service.impl;

import com.resume.entity.CurrentProfile;
import com.resume.entity.Profile;
import com.resume.service.SecurityService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public CurrentProfile getCurrentAuthorizedProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CurrentProfile) {
            return ((CurrentProfile)principal);
        } else {
            return null;
        }
    }

    @Override
    public void authenticate(Profile profile) {
        CurrentProfile currentProfile = new CurrentProfile(profile);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                currentProfile, currentProfile.getPassword(), currentProfile.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public Long getCurrentProfileId() {
        CurrentProfile currentProfile = getCurrentAuthorizedProfile();
        return currentProfile != null ? currentProfile.getId() : null;
    }
}
