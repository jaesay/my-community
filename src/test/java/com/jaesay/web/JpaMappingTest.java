package com.jaesay.web;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jaesay.web.domain.Board;
import com.jaesay.web.domain.User;
import com.jaesay.web.domain.enums.BoardType;
import com.jaesay.web.repository.BoardRepository;
import com.jaesay.web.repository.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {

	private final String boardTestTitle = "테스트";
	private final String email = "jaesay@gmail.com";
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Before
	public void init() {
		User user = userRepository.save(User.builder()
				.name("jaeseong")
				.password("1234")
				.email(email)
				.createdDate(LocalDateTime.now())
				.build());
		
		boardRepository.save(Board.builder()
				.title(boardTestTitle)
				.subTitle("서브 타이틀")
				.content("컨텐츠")
				.boardType(BoardType.free)
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.user(user).build());
	}
	
	@Test
	public void saveSuccessTest() {
		User user = userRepository.findByEmail(email);
		assertThat(user.getName(), is("jaeseong"));
		assertThat(user.getPassword(), is("1234"));
		assertThat(user.getEmail(), is(email));
		
		Board board = boardRepository.findByUser(user);
		assertThat(board.getTitle(), is(boardTestTitle));
		assertThat(board.getSubTitle(), is("서브 타이틀"));
		assertThat(board.getContent(), is("컨텐츠"));
		assertThat(board.getBoardType(), is(BoardType.free));
	}
	
}
