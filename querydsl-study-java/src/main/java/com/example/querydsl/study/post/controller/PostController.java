package com.example.querydsl.study.post.controller;

import com.example.querydsl.study.post.dto.PostDto;
import com.example.querydsl.study.post.dto.PostSave;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.post.service.PostService;
import com.example.querydsl.study.user.dto.UserReference;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ApiOperation("게시글 등록 api")
    public PostDto save(@Validated({PostSave.class, UserReference.class}) @RequestBody PostDto postDto) {
        return null;
    }

    @GetMapping
    @ApiOperation("게시글 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createdAt", value = "등록일", paramType = "query"),
    })
    public Page<PostDto> find(@QuerydslPredicate(root = Post.class) Predicate predicate,
                              @PageableDefault Pageable pageable) {
        return postService.findAll(predicate, pageable);
    }
}
