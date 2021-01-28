package com.example.querydsl.study.comment.entity;

import com.example.querydsl.study.core.entity.BaseEntity;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.user.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne
    private User writer;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Comment> children;
}
