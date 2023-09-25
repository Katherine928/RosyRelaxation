package com.katherine.rosyrelaxationbackend.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


class PasswordEncoderTest {

    @Test
    void testEncodedPassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123456abc";
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
        boolean matches = bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
        assertThat(matches).isTrue();
    }
}
