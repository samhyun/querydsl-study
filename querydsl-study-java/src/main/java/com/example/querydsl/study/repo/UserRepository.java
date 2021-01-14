package com.example.querydsl.study.repo;

import com.example.querydsl.study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, QUserRepository {
}
