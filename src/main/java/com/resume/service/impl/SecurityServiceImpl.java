package com.resume.service.impl;

import com.resume.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public Long getCurrentProfileId() {
        //TODO изменить id профиля на текущего пользователя
        return 13L;
    }
}
