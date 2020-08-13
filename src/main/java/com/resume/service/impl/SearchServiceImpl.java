package com.resume.service.impl;

import com.resume.entity.Profile;
import com.resume.entity.SkillCategory;
import com.resume.exception.ProfileNotFoundException;
import com.resume.repository.search.ProfileSearchRepository;
import com.resume.repository.storage.ProfileRepository;
import com.resume.repository.storage.SkillCategoryRepository;
import com.resume.service.SearchService;
import com.resume.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService {

    private final SecurityService securityService;
    private final SkillCategoryRepository skillCategoryRepository;
    private final ProfileRepository profileRepository;
    private final ProfileSearchRepository profileSearchRepository;

    @Autowired
    public SearchServiceImpl(SecurityService securityService, SkillCategoryRepository skillCategoryRepository, ProfileRepository profileRepository, ProfileSearchRepository profileSearchRepository) {
        this.securityService = securityService;
        this.skillCategoryRepository = skillCategoryRepository;
        this.profileRepository = profileRepository;
        this.profileSearchRepository = profileSearchRepository;
    }

    @Override
    public Profile getCurrentProfile() throws ProfileNotFoundException {
        Long currentProfileId = securityService.getCurrentProfileId();
        Profile byId = profileRepository.findOne(currentProfileId);
        if (byId == null) throw new ProfileNotFoundException();
        return byId;
    }

    @Override
    @Transactional
    public Iterable<Profile> findAllProfilesForIndexing() {
        Iterable<Profile> all = profileRepository.findAll();
        for (Profile p : all) {
            p.getSkills().size();
            p.getCertificates().size();
            p.getLanguages().size();
            p.getPractics().size();
            p.getCourses().size();
        }
        return all;
    }

    @Override
    public Page<Profile> findAllProfilesPage(PageRequest pageRequest) {
        return profileRepository.findAll(pageRequest);
    }

    @Override
    public Profile findProfileByUid(String uid) {
        Profile byUid = profileRepository.findByUid(uid);
        if (byUid == null) throw new ProfileNotFoundException();
        return byUid;
    }

    @Override
    public Iterable<SkillCategory> getAllSkillCategories() {
        return skillCategoryRepository.findAll();
    }

    @Override
    public Page<Profile> findBySearchQuery(String query, Pageable pageable) {
        return profileSearchRepository.findByObjectiveLikeOrSummaryLikeOrInfoLike
                (query, query, query, pageable);
    }
}
