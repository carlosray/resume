package com.resume.service.impl;

import com.resume.entity.CurrentProfile;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchServiceImpl implements SearchService, UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = findProfile(username);
        if (profile != null) {
            return new CurrentProfile(profile);
        }
        else {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }

    private Profile findProfile(String anyUnigueId) {
        Profile profile = profileRepository.findByUid(anyUnigueId);
        if (profile == null) {
            profile = profileRepository.findByContactsProfileEmail(anyUnigueId);
            if (profile == null) {
                profile = profileRepository.findByContactsProfilePhone(anyUnigueId);
            }
        }
        return profile;
    }
}
