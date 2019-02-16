package com.jaesay.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jaesay.web.domain.Board;
import com.jaesay.web.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public Page<Board> findBoardList(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
		
		return boardRepository.findAll(pageable);
	}
	
	public Board findBoardByIdx(Long idx) {
		return boardRepository.findById(idx).orElse(new Board());
	}
}
