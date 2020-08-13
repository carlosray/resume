package com.resume.event;

import com.resume.entity.Profile;

public class UpdateProfileEvent extends UpdateEntityEvent<Profile> {
    public UpdateProfileEvent(Profile object) {
        super(object);
    }
}
