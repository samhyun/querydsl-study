package com.example.querydsl.study.post.dto;

import com.example.querydsl.study.comment.dto.CommentDto;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.user.dto.UserDto;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.querydsl.core.annotations.QueryProjection;
import com.zaxxer.hikari.metrics.PoolStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PostDto {

    private Long id;

    @NotNull(groups = PostSave.class)
    private String title;

    @NotNull(groups = PostSave.class)
    private String content;

    @NotNull(groups = PostSave.class)
    @Valid
    private UserDto writer;

    private Date createdAt;

    private long commentCount;

    private List<CommentDto> comments;

    @QueryProjection
    public PostDto(long id, String title, UserDto writer, Date createdAt) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createdAt = createdAt;
    }

    @QueryProjection
    public PostDto(long id, String title, String content, UserDto writer, Date createdAt, long commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
    }

    @QueryProjection
    @Builder
    public PostDto(long id, String title, String content, UserDto writer, Date createdAt, long commentCount, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
        this.comments = comments;
    }

    public Post convertToPost() {
        Post post = new Post();
        BeanUtils.copyProperties(this, new Post());
        return post;
    }
}
