package com.kh.mybatis.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

public class BoardDao {

// 230307 5교시
	// 게시글 수 조회 테스트
	public int getBoardCount(SqlSession session) {

		return session.selectOne("boardMapper.selectBoardCount");
	}

// 230307 6교시
	// 게시글 목록 조회 테스트 목록 페이징 처리
	public List<Board> findAll(SqlSession session, PageInfo pageInfo) {
		/*
		 * mybatis에서 페이징 처리
		 * 
		 * 	mybatis에서는 페이징 처리를 위해 RowBounds라는 클래스를 제공해 준다.
		 * 	RowBounds의 인스턴스 생성할 때 offset과 limit 값을 전달해서 페이징 처리를 구현한다.
		 * 	(offset만큼 건너뛰고 limit만큼 가져온다.)
		 * 
		 * 	ex) offset = 0, limit = 10
		 * 		- 전체 조회된 게시글에서 0개를 건너뛰고 10개를 가져온다. (1 ~ 10)
		 * 		offset = 10, limit = 10
		 * 		- 10개를 건너뛰고 10개를 가져온다. (11 ~ 20)
		 * 		offset = 20, limit = 10
		 * 		- 20개를 건너뛰고 10개를 가져온다. (21 ~ 30)
		 * 
		 */
		int limit = pageInfo.getListLimit();
		int offset = (pageInfo.getCurrentPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return session.selectList("boardMapper.selectAll", null, rowBounds);
	}
	
// 230307 8교시
	// 게시글 수 조회(검색 기능 적용) 테스트
	public int getBoardCount(SqlSession session, String writer, String title, String content) {
		Map<String, String> map = new HashMap<>();
		// ㄴ<키, 값>						 ㄴ 앞에서 지정했기에 유추 할 수 있어서 <키 자료형, 값 자료형>생략 가능
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
		
		return session.selectOne("boardMapper.selectBoardCountByKeyWord", map);
	}
	
// 230307 7교시
	// 게시글 목록 조회(검색 기능 적용) 테스트
	public List<Board> findAll(SqlSession session, String writer, String title, String content) {
		Map<String, String> map = new HashMap<>();
			// ㄴ<키, 값>					 ㄴ 앞에서 지정했기에 유추 할 수 있어서 <키 자료형, 값 자료형>생략 가능
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
			
		return session.selectList("boardMapper.selectAllByKeyWord", map);
					// selectList 보면 우리가 넘겨야 하는 파라미터는 세 갠데 하나의 오브젝트만 파라미터로 넘겨주게 되어있음.
					// 해결법. Map으로 담아서 전달을 해줌 
	}

}
