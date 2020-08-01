package com.resume.service;

import com.resume.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PublicDataService {
    Profile findProfileByUid(String uid);
    Page<Profile> getProfilePage(PageRequest pageRequest);
}
