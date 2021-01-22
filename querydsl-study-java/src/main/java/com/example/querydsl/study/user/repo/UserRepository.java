package com.example.querydsl.study.user.repo;

import com.example.querydsl.study.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QUserRepository {
}
