package com.example.querydsl.study.comment.repo;

import com.example.querydsl.study.comment.dto.CommentDto;
import com.example.querydsl.study.comment.entity.Comment;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.post.repo.PostRepository;
import com.example.querydsl.study.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
@Sql(scripts = {
        "classpath:data/user.sql",
        "classpath:data/board.sql",
        "classpath:data/post.sql"
})
public class CommentRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Post post;

    private User writer;

    private Comment comment;
    private Comment savedComment;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        post = Post.builder().id(1).build();
        writer = User.builder().id(1).build();

        comment = Comment.builder()
                .post(post)
                .writer(writer)
                .content("hihihiih")
                .build();

        savedComment = commentRepository.save(
                comment
        );
        testEntityManager.flush();
    }

    @Test
    void update_comment_test() {
    }


}
