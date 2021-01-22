package com.example.querydsl.study.board.repo;

import com.example.querydsl.study.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, QBoardRepository {
}
