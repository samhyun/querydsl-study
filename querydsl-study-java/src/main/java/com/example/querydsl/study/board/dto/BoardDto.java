package com.example.querydsl.study.board.dto;

import com.example.querydsl.study.board.entity.BoardType;
import com.example.querydsl.study.post.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDto {

    private Long id;

    private String name;

    private BoardType type;

    private List<PostDto> posts;

    private Long postCount;

    @QueryProjection
    public BoardDto(long id, String name, BoardType type, long postCount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.postCount = postCount;
    }

    @QueryProjection
    public BoardDto(long id, String name, BoardType type, List<PostDto> posts) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.posts = posts;
    }
}
