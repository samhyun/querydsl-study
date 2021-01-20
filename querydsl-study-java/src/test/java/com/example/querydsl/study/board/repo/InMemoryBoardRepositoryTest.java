package com.example.querydsl.study.board.repo;

import com.example.querydsl.study.board.dto.BoardDto;
import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.board.entity.BoardType;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.post.repo.PostRepository;
import com.example.querydsl.study.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Sql(scripts = {
        "classpath:data/user.sql",
        "classpath:data/board.sql",
        "classpath:data/post.sql"
})
public class InMemoryBoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    private Board communityBoard;

    private Board blogBoard;

    @BeforeEach
    void setUp() {
        communityBoard = boardRepository.save(
                Board.builder()
                        .name("테스트 커뮤니티")
                        .type(BoardType.COMMUNITY)
                        .build()
        );
        blogBoard = boardRepository.save(
                Board.builder()
                        .name("테스트 블로그")
                        .type(BoardType.BLOG)
                        .build()
        );

        User writer = User.builder().id(1).build();

        postRepository.save(
                Post.builder()
                        .title("테스트 포스트 1")
                        .content("테스트 컨텐츠 1")
                        .writer(writer)
                        .board(communityBoard)
                        .build()
        );

        postRepository.save(
                Post.builder()
                        .title("테스트 포스트 2")
                        .content("테스트 컨텐츠 2")
                        .writer(writer)
                        .board(communityBoard)
                        .build()
        );


        postRepository.save(
                Post.builder()
                        .title("테스트 포스트 3")
                        .content("테스트 컨텐츠 3")
                        .writer(writer)
                        .board(blogBoard)
                        .build()
        );
    }

    @Test
    public void select_board_list_test() {
        List<BoardDto> boards = boardRepository.findBoards(null);

        boards.forEach(board -> {
            if (board.getId() == communityBoard.getId()) {
                communityBoard_test(board);
            }
            if (board.getId() == blogBoard.getId()) {
                blogBoard_test(board);
            }

            assertNull(board.getPosts());
        });
    }

    private void blogBoard_test(BoardDto board) {
        assertEquals(1, board.getPostCount());
        assertEquals(BoardType.BLOG, board.getType());
    }

    private void communityBoard_test(BoardDto board) {
        assertEquals(2, board.getPostCount());
        assertEquals(BoardType.COMMUNITY, board.getType());
    }
}
