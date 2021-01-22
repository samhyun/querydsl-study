package com.example.querydsl.study.user.repo;

import com.example.querydsl.study.user.dto.UserDto;
import com.example.querydsl.study.user.entity.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"classpath:data/user.sql"})
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User savedUser;
    @BeforeEach
    void setUp() {
        savedUser = userRepository.save(
                User.builder()
                        .email("test@test.com")
                        .nickname("test_nickname")
                        .firstName("first1")
                        .lastName("last1")
                        .build());
    }

    /**
     * 유저 등록 테스트
     */
    @Test
    public void userInsertTest() {
        long count = userRepository.count();

        userRepository.save(
                User.builder()
                        .email("test2@test.com")
                        .nickname("test_nickname2")
                        .firstName("first2")
                        .lastName("last2")
                        .build());

        Assertions.assertEquals(count + 1, userRepository.count());
    }

    /**
     * 이미 등록된 닉네임으로 유저 등록 테스트
     */
    @Test
    public void userInsertExistsNicknameTest() {
        User existsUser = User.builder()
                .email("test@test.com")
                .nickname("test_nickname")
                .firstName("first1")
                .lastName("last1")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(existsUser);
        });
    }

    /**
     * 유저 정보 조회 테스트
     */
    @Test
    public void select_detail_user_test() {
        UserDto userDto = userRepository.getById(savedUser.getId());

        Assertions.assertEquals(savedUser.getNickname(), userDto.getNickname());
        Assertions.assertEquals(savedUser.getLastName() + savedUser.getFirstName(), userDto.getName());
        Assertions.assertEquals(savedUser.getId(), userDto.getId());
    }

}
