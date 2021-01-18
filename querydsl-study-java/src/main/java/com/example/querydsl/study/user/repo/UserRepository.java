package com.example.querydsl.study.user.repo;

import com.example.querydsl.study.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, QUserRepository {
}
