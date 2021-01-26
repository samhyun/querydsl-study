package com.example.querydsl.study.post.repo;

import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.board.entity.BoardType;
import com.example.querydsl.study.board.repo.BoardRepository;
import com.example.querydsl.study.comment.entity.Comment;
import com.example.querydsl.study.comment.repo.CommentRepository;
import com.example.querydsl.study.post.dto.PostDto;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import static com.example.querydsl.study.post.entity.QPost.post;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = {
        "classpath:data/user.sql",
        "classpath:data/board.sql",
        "classpath:data/post.sql"
})
public class PostRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Board communityBoard;

    private Post detailPost;
    private User writer;

    @BeforeEach
    void setUp() {
        communityBoard = boardRepository.save(
                Board.builder()
                        .name("테스트 커뮤니티")
                        .type(BoardType.COMMUNITY)
                        .build()
        );

        writer = User.builder().id(1).build();

        detailPost = postRepository.save(
                createPostInstance("상세조회 포스트")
        );

        for (int i = 0; i < 30; i++) {
            Post firstPost = postRepository.save(
                    createPostInstance(String.format("b-%d", i + 1))
            );

            Post secondPost = postRepository.save(
                    createPostInstance(String.format("a-%d", i + 1))
            );

            for (int j = 0; j < 30; j++) {
                commentRepository.save(
                        createCommentInstance(detailPost)
                );

                commentRepository.save(
                        createCommentInstance(firstPost)
                );

                commentRepository.save(
                        createCommentInstance(secondPost)
                );
            }
        }
    }

    /**
     * 포스트 페이지네이션 조회 테스트
     */
    @Test
    public void select_post_page_test() {
        Page<PostDto> postPage = postRepository.findPosts(
                post.board.id.eq(communityBoard.getId()),
                PageRequest.of(0, 5, Sort.by(Sort.Order.desc("createdAt"), Sort.Order.asc("title")))
        );

        assertNotNull(postPage);
        assertEquals(5, postPage.getSize());
        assertEquals("a-30", postPage.getContent().get(0).getTitle());
    }


    @Test
    public void select_post_detail_test() {
        PostDto postDto = postRepository.getPost(detailPost.getId());

        assertNotNull(postDto);
        assertEquals(900, postDto.getCommentCount());
        assertNull(postDto.getComments());

        PostDto postDtoWithComments = postRepository.getPostWithComments(detailPost.getId());
        assertEquals(900, postDtoWithComments.getCommentCount());
        assertNotNull(postDtoWithComments.getComments());
        assertEquals(2, postDtoWithComments.getComments().get(0).getWriter().getId());
    }

    @Test
    public void insert_post_test() {
        Post savePost = postRepository.save(
                createPostInstance("등록 테스트")
        );
        assertNotEquals(0, savePost.getId());
        PostDto postDto = postRepository.getPost(savePost.getId());
        assertEquals(savePost.getTitle(), postDto.getTitle());
    }

    @Test
    public void insert_abnormal_post_test() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            Post post = createPostInstance("test");
            post.setWriter(null);
            postRepository.save(post);
        });
        assertThrows(DataIntegrityViolationException.class, () -> {
            Post post = createPostInstance("test");
            post.setBoard(null);
            postRepository.save(post);
        });
        assertThrows(DataIntegrityViolationException.class, () -> {
            Post post = createPostInstance("test");
            post.setTitle(null);
            postRepository.save(post);
        });
    }

    private Post createPostInstance(String title) {
        return Post.builder()
                .board(communityBoard)
                .title(title)
                .content("test")
                .writer(writer)
                .build();
    }

    private Comment createCommentInstance(Post post) {
        return Comment.builder()
                .content("test")
                .writer(User.builder().id(2).build())
                .post(post)
                .build();
    }

}
