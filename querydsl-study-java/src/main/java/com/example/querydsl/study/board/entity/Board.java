package com.example.querydsl.study.board.entity;

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
public class Board extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private BoardType type;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Post> posts;
}
