package com.resume.service;

import com.resume.entity.Hobby;
import com.resume.entity.Profile;

import java.util.List;
import java.util.Set;

public interface HobbyService {
    Set<Hobby> getAllHobbiesListWithSelected (List<Hobby> hobbies);
    List<Hobby> getHobbiesByName(List<String> hobbies, Profile currentProfile);
}
