package com.example.querydsl.study.post.entity;

import com.example.querydsl.study.core.entity.BaseEntity;
import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.comment.entity.Comment;
import com.example.querydsl.study.user.entity.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {

    @Id
    @Generated
    private long id;

    private String title;

    private String content;

    @ManyToOne
    private User writer;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
