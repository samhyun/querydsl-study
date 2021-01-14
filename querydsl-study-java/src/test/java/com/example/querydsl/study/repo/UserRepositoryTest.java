package com.example.querydsl.study.repo;

import com.example.querydsl.study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void select_user_test() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
    }
}
