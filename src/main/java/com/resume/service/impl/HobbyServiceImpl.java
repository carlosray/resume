package com.resume.service.impl;

import com.resume.entity.Hobby;
import com.resume.entity.Profile;
import com.resume.service.HobbyService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HobbyServiceImpl implements HobbyService {
    @Override
    public Set<Hobby> getAllHobbiesListWithSelected(List<Hobby> hobbies) {
        final Set<Hobby> allHobbies = getAllHobbies();
        allHobbies.forEach(hobby -> {
            if (hobbies.contains(hobby))
                hobby.setSelected(true);
        });
        return allHobbies;
    }

    private Set<Hobby> getAllHobbies() {
        return new TreeSet<>(Arrays.asList(new Hobby("Cycling"), new Hobby("Handball"), new Hobby("Football"), new Hobby("Basketball"),
                new Hobby("Bowling"), new Hobby("Boxing"), new Hobby("Volleyball"), new Hobby("Baseball"), new Hobby("Skating"),
                new Hobby("Skiing"), new Hobby("Table tennis"), new Hobby("Tennis"), new Hobby("Weightlifting"),
                new Hobby("Automobiles"), new Hobby("Book reading"), new Hobby("Cricket"), new Hobby("Photo"),
                new Hobby("Shopping"), new Hobby("Cooking"), new Hobby("Codding"), new Hobby("Animals"), new Hobby("Traveling"),
                new Hobby("Movie"), new Hobby("Painting"), new Hobby("Darts"), new Hobby("Fishing"), new Hobby("Kayak slalom"),
                new Hobby("Games of chance"), new Hobby("Ice hockey"), new Hobby("Roller skating"), new Hobby("Swimming"),
                new Hobby("Diving"), new Hobby("Golf"), new Hobby("Shooting"), new Hobby("Rowing"), new Hobby("Camping"),
                new Hobby("Archery"), new Hobby("Pubs"), new Hobby("Music"), new Hobby("Computer games"), new Hobby("Authorship"),
                new Hobby("Singing"), new Hobby("Foreign lang"), new Hobby("Billiards"), new Hobby("Skateboarding"),
                new Hobby("Collecting"), new Hobby("Badminton"), new Hobby("Disco")));
    }

    @Override
    public List<Hobby> getHobbiesByName(List<String> hobbies, Profile currentProfile) {
        List<Hobby> profileHobbies = new ArrayList<>();
        hobbies.forEach(name -> profileHobbies.add(new Hobby(name, currentProfile)));
        return profileHobbies;
    }
}
