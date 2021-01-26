package com.example.querydsl.study.post.controller;

import com.example.querydsl.study.post.dto.PostDto;
import com.example.querydsl.study.post.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ApiOperation("게시글 등록 api")
    public PostDto save(@Valid @RequestBody PostDto postDto) {
        return null;
    }
}
