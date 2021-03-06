package com.example.querydsl.study.user.entity;

import com.example.querydsl.study.core.entity.BaseEntity;
import com.example.querydsl.study.post.entity.Post;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

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
}
