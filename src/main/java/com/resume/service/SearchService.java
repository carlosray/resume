package com.resume.service;

import com.resume.entity.Profile;
import com.resume.entity.SkillCategory;
import com.resume.exception.ProfileNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Iterable<Profile> findAllProfilesForIndexing();
    Page<Profile> findAllProfilesPage(PageRequest pageRequest);
    Profile findProfileByUid(String uid) throws ProfileNotFoundException;
    Profile getCurrentProfile() throws ProfileNotFoundException;
    Iterable<SkillCategory> getAllSkillCategories();
    Page<Profile> findBySearchQuery(String query, Pageable pageable);
}
