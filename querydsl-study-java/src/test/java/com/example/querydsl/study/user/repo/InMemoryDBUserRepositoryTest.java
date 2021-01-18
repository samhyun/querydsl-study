package com.example.querydsl.study.user.repo;

import com.example.querydsl.study.user.dto.UserDto;
import com.example.querydsl.study.user.entity.User;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.PersistenceException;
import java.util.List;

@DataJpaTest
public class InMemoryDBUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        userRepository.save(
                User.builder()
                        .email("test@test.com")
                        .nickname("test_nickname")
                        .firstName("first1")
                        .lastName("last1")
                        .build());
    }

    @Test
    public void userInsertTest() {
        userRepository.save(
                User.builder()
                        .email("test2@test.com")
                        .nickname("test_nickname2")
                        .firstName("first2")
                        .lastName("last2")
                        .build());

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void userInsertExistsNicknameTest() {
//        DataIntegrityViolationException
//        given
        User existsUser = User.builder()
                .email("test@test.com")
                .nickname("test_nickname")
                .firstName("first1")
                .lastName("last1")
                .build();

//        when
        userRepository.save(existsUser);

        Assertions.assertThrows(PersistenceException.class, () -> {
            testEntityManager.flush();
        });
    }

    @Test
    public void userQueryProjectionsTest() {
        UserDto userDto = userRepository.getById(1);

        Assertions.assertEquals("first1 last1", userDto.getName());
        Assertions.assertEquals(1, userDto.getId());
    }

}
