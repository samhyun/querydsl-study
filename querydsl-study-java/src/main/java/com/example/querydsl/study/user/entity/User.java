package com.example.querydsl.study.user.entity;

import com.example.querydsl.study.core.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String password;

    private String firstName;

    private String lastName;

    private String mobile;

}
