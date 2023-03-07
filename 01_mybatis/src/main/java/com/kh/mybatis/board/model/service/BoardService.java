package com.kh.mybatis.board.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

import static com.kh.mybatis.common.template.SqlSessionTemplate.getSession;

import java.util.List;

// 230307 4교시
			// r ctrl 1로 BoardServiceTest 클래스 생성
public class BoardService {

// 230307 4교시 5교시
	// 게시글 수 조회 테스트
	public int getBoardCount() {
		int count = 0;		// alt shift r 하면 한 번에 여러 개의 이름 변경 가능
//		SqlSession session = SqlSessionTemplate.getSession();	// import static으로 변경해서 간단히 작성 가능
		SqlSession session = getSession();
		
		count = new BoardDao().getBoardCount(session);
		
		session.close();
		
		return count;
	}

// 230307 5교시 6교시
	// 게시글 목록 조회 테스트
	public List<Board> findAll(PageInfo pageInfo) {	// 230307 5교시 pageInfo 파라미터 추가
		// 230307 6교시
		List<Board> list = null;
		SqlSession session = getSession();
		
		list = new BoardDao().findAll(session, pageInfo);
		
		session.close();
		
		return list;
	}

// 230307 7교시
	// 게시글 목록 조회(검색 기능 적용) 테스트
	public List<Board> findAll(String writer, String title, String content) {
		List<Board> list = null;
		
		SqlSession session = getSession();
		
		list = new BoardDao().findAll(session, writer, title, content);	
		
		session.close();
		
		return list;
	}

// 230307 8교시
	// 게시글 수 조회(검색 기능 적용) 테스트
	public int getBoardCount(String writer, String title, String content) {
		int count = 0;
		SqlSession session = getSession();
		
		count = new BoardDao().getBoardCount(session, writer, title, content);
										// 매개값 없으면 전체 게시글 조회. 매개값 넣어줌
		
		session.close();
		
		return count;
	}
	
}
