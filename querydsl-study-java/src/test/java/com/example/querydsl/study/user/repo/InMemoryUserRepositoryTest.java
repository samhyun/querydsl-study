package com.example.querydsl.study.user.repo;

import com.example.querydsl.study.user.dto.UserDto;
import com.example.querydsl.study.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"classpath:data/user.sql"})
@DataJpaTest
public class InMemoryUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private TestEntityManager testEntityManager;

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
//        given
        long count = userRepository.count();
//        when
        userRepository.save(
                User.builder()
                        .email("test2@test.com")
                        .nickname("test_nickname2")
                        .firstName("first2")
                        .lastName("last2")
                        .build());

//       then
        Assertions.assertEquals(count + 1, userRepository.count());
    }

    @Test
    public void userInsertExistsNicknameTest() {
//        given
        User existsUser = User.builder()
                .email("test@test.com")
                .nickname("test_nickname")
                .firstName("first1")
                .lastName("last1")
                .build();

//        when
//        then
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(existsUser);
        });
    }

    @Test
    public void userQueryProjectionsTest() {
        UserDto userDto = userRepository.getById(1);

        Assertions.assertEquals("first1 last1", userDto.getName());
        Assertions.assertEquals(1, userDto.getId());
    }

}
