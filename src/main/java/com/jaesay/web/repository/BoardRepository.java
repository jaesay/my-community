package com.jaesay.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaesay.web.domain.Board;
import com.jaesay.web.domain.User;

public interface BoardRepository extends JpaRepository<Board, Long>{

	Board findByUser(User user);

}
