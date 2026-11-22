package com.caue.testforstudy.repository;

import com.caue.testforstudy.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
    }
    @Test
    @DisplayName("Should save user in database")
    void shouldSaveUser(){
        User user = User.builder().name("Cauê").email("caue@gmail.com").build();
        User save = userRepository.save(user);

        Assertions.assertEquals(save.getName(), user.getName());
    }
}