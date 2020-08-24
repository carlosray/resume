package com.resume.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SearchServiceImplTest {

    private PasswordEncoder encoder = new StandardPasswordEncoder("53cr3t");

    @Test
    public void testEncoder() {
        System.out.println(encoder.encode("password"));
        assertTrue(encoder.matches("password", "f613be56474e7214c90572b0d83ff5feb21d97f007a11b03ff0b425cd66c2899d702db3348e03a20"));
    }

}