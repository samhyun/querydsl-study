package com.example.querydsl.study.comment.repo;

import com.example.querydsl.study.comment.dto.CommentDto;
import com.example.querydsl.study.comment.entity.Comment;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.post.repo.PostRepository;
import com.example.querydsl.study.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import static com.example.querydsl.study.comment.entity.QComment.comment;
import static org.junit.jupiter.api.Assertions.assertEquals;

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


    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        post = Post.builder().id(1).build();
        writer = User.builder().id(1).build();

        for (int i = 0; i < 100; i++) {
            commentRepository.save(Comment.builder()
                    .post(post)
                    .writer(writer)
                    .content("test comment - " + i)
                    .build());
        }
    }

    @Test
    public void select_comment_page_test() {
        Page<CommentDto> commentPage = commentRepository.findComments(
                comment.post.id.eq(post.getId()),
                PageRequest.of(1, 5, Sort.by(Sort.Order.desc("createdAt"), Sort.Order.asc("content")))
        );

        assertEquals(100, commentPage.getTotalElements());
        assertEquals(1, commentPage.getNumber());
        assertEquals(5, commentPage.getNumberOfElements());
    }


}
