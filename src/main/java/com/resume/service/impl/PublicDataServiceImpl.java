package com.resume.service.impl;

import com.resume.entity.Profile;
import com.resume.repository.ProfileRepository;
import com.resume.service.PublicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublicDataServiceImpl implements PublicDataService {

    private final ProfileRepository profileRepository;

    @Value("${profiles.perPage:10}")
    private int profilesPerPage;

    @Autowired
    public PublicDataServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public Profile findProfileByUid(String uid) {
        return profileRepository.findByUid(uid);
    }

    @Override
    @Transactional
    public Page<Profile> getProfilePage(PageRequest pageRequest) {
        return profileRepository.findAll(pageRequest);
    }
}
