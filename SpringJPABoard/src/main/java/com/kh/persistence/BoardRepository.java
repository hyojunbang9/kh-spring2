package com.kh.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
