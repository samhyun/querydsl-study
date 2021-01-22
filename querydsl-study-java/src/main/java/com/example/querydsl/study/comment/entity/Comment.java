package com.example.querydsl.study.comment.entity;

import com.example.querydsl.study.core.entity.BaseEntity;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.user.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    private long id;

    private String content;

    @ManyToOne
    private User writer;

    @ManyToOne
    private Post post;
}
