package com.example.querydsl.study.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String password;

    private String lastName;

    private String firstName;

    private String mobile;

    @OneToMany(mappedBy = "writer")
    private List<Post> posts;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date modifiedAt;
}