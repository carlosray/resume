package com.resume.repository;

import com.resume.entity.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {
    Profile findByUid(String uid);
}
