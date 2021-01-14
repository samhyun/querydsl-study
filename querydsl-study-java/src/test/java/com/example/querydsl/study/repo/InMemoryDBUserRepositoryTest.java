package com.example.querydsl.study.repo;

import com.example.querydsl.study.dto.UserDto;
import com.example.querydsl.study.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class InMemoryDBUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(
                User.builder()
                        .email("test@test.com")
                        .firstName("first1")
                        .lastName("last1")
                        .build());
    }

    @Test
    public void userInsertTest() {
        userRepository.save(
                User.builder()
                        .email("test2@test.com")
                        .firstName("first2")
                        .lastName("last2")
                        .build());

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void userQueryProjectionsTest() {
        UserDto userDto = userRepository.getById(1);

        Assertions.assertEquals("first1 last1", userDto.getName());
        Assertions.assertEquals(1, userDto.getId());
    }

}
