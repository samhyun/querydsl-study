package com.example.querydsl.study.post.entity;

import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.comment.entity.Comment;
import com.example.querydsl.study.core.entity.BaseEntity;
import com.example.querydsl.study.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Column(nullable = false)
    private String title;

    private String content;

    @ManyToOne(optional = false)
    private User writer;

    @ManyToOne(optional = false)
    private Board board;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.REMOVE})
    private List<Comment> comments;

}
