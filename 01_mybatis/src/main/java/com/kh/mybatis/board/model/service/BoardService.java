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
	// 게시글 수 조회 테스트, 게시글 목록 조회 테스트
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

// 230307 7교시
	// 게시글 목록 조회(검색 기능 적용) 테스트
// 230308 1교시
	// 페이징 처리					r pageInfo 추가
	public List<Board> findAll(PageInfo pageInfo, String writer, String title, String content) {
		List<Board> list = null;
		SqlSession session = getSession();
											// r pageInfo 추가
		list = new BoardDao().findAll(session, pageInfo, writer, title, content);	
							// ㄴ change Method 검색기능구현메소드인지 주의!
		session.close();
		
		return list;
	}
	

//	230308 3교시
	// 게시글 수 조회(검색 기능 적용 2) 테스트	
	public int getBoardCount(String type, String keyword) {
		int count = 0;
		SqlSession session = getSession();
		
		count = new BoardDao().getBoardCount(session, type, keyword);
		
		session.close();
		
		return count;
	}
	
// 230308 2교시 3교시(페이징 처리)
	// 게시글 목록 조회(검색 기능 적용2) 테스트
	// 메소드 오버로딩으로 동일한 이름의 메소드 생성
	public List<Board> findAll(PageInfo pageInfo, String type, String keyword) {
		List<Board> list = null;
		SqlSession session = getSession();
		
		list = new BoardDao().findAll(session, pageInfo, type, keyword);
		
		session.close();
		
		return list;
	}


// 230308 5교시
	// 게시글 수 조회(필터 기능 적용) 테스트 페이징처리 2페이지 출력해보기	
	public int getBoardCount(String[] filters) {
		int count = 0;
		SqlSession session = getSession();
		
		count = new BoardDao().getBoardCount(session, filters);
		
		session.close();
		
		return count;
	}	
	
// 230308 4교시 5교시(페이징 처리)
	// 게시글 목록 조회(필터 기능 적용) 테스트
	public List<Board> findAll(PageInfo pageInfo, String[] filters) {
		
		List<Board> list = null;
		SqlSession session = getSession();
		
		list = new BoardDao().findAll(session, pageInfo, filters);
		
		session.close();
		
		return list;
	}

	
// 230308 6교시
	// 게시글 상세 조회(댓글 포함) 테스트	
	public Board getBoardByNo(int no) {	// no = 게시글 번호
		Board board = null;	// Board 타입의 참조변수 board 
		SqlSession session = getSession();
		
		board = new BoardDao().findBoardByNo(session, no);
		
		session.close();
		
		return board;
	}
	
}
