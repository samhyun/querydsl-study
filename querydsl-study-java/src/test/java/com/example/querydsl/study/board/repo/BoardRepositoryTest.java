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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.example.querydsl.study.board.entity.QBoard.board;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = {
        "classpath:data/user.sql",
        "classpath:data/board.sql",
        "classpath:data/post.sql"
})
public class BoardRepositoryTest {

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

    /**
     * 게시판 목록 조회 테스트
     */
    @Test
    public void select_board_list_test() {
        List<BoardDto> boards = boardRepository.findBoards(board.type.in(BoardType.COMMUNITY, BoardType.BLOG), board.id.desc());

        boards.forEach(board -> {
            if (board.getId() == communityBoard.getId()) {
                assertEquals(2, board.getPostCount());
                assertEquals(BoardType.COMMUNITY, board.getType());
            }
            if (board.getId() == blogBoard.getId()) {
                assertEquals(1, board.getPostCount());
                assertEquals(BoardType.BLOG, board.getType());
            }

            assertNull(board.getPosts());
        });
    }

    /**
     * 게시판 상세 조회 테스트
     */
    @Test
    public void select_board_detail_test() {
        BoardDto board = boardRepository.getBoard(1);

        assertNull(board.getPostCount());
        assertEquals(2, board.getPosts().size());
        assertEquals("testNotice!!!", board.getName());
        assertEquals(BoardType.NOTICE, board.getType());

        board.getPosts().forEach(post -> {
            assertNotNull(post.getWriter());
        });
    }

    /**
     * 게시판 조회 등록 테스트
     */
    @Test
    public void insert_board_test() {
        long count = boardRepository.count();
        Board board = boardRepository.save(
                Board.builder()
                        .name("새로 등록한 게시판")
                        .type(BoardType.COMMUNITY)
                        .build()
        );

        assertNotEquals(0, board.getId());
        assertEquals(count + 1, boardRepository.count());

    }

    /**
     * 이름이 null 인 게시판 등록 테스트
     */
    @Test
    public void test_insert_name_null() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            boardRepository.save(
                    Board.builder()
                            .type(BoardType.COMMUNITY)
                            .build()
            );
        });
    }
}
