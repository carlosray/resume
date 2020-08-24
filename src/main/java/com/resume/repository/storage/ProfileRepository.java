package com.resume.repository.storage;

import com.resume.entity.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {
    Profile findByUid(String uid);

    Profile findByContactsProfileEmail(String email);

    Profile findByContactsProfilePhone(String phone);
}
