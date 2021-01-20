package com.example.querydsl.study.post.entity;

import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.core.entity.BaseEntity;
import com.example.querydsl.study.user.entity.User;
import lombok.*;

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
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String content;

    @ManyToOne
    private User writer;

    @ManyToOne
    private Board board;

}
