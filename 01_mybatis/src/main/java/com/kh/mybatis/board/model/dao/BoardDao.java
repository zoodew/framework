package com.kh.mybatis.board.model.dao;

import java.util.Arrays;
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
						// getCurrentPage = 현재 페이지
						// (1페이지 - 1) * 10 > offset = 0
						// (2페이지 - 1) * 10 > offset = 10
						// (3페이지 - 1) * 10 > offset = 20	이 offset 값을 갖고 위의 주석 ex를 참고하기
		
		RowBounds rowBounds = new RowBounds(offset, limit);
														// r 파라미터 값이 없으니 null이라고 적어줌
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
// 230308 1교시
	// 페이징 처리
	public List<Board> findAll(SqlSession session, PageInfo pageInfo, String writer, String title, String content) {
		// 페이징 처리
		int limit = pageInfo.getListLimit();
		int offset = (pageInfo.getCurrentPage() - 1) * limit;		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		Map<String, String> map = new HashMap<>();
			// ㄴ<키, 값>					 ㄴ 앞에서 지정했기에 유추 할 수 있어서 <키 자료형, 값 자료형>생략 가능
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
																// 위의 전체 게시글 목록 페이징 처리는 파라미터 값이 없어서 null, 여긴 파라미터값이 있어서 map
		return session.selectList("boardMapper.selectAllByKeyWord", map, rowBounds);
					// selectList() 보면 우리가 넘겨야 하는 파라미터는 세 갠데 하나의 오브젝트만 파라미터로 넘겨주게 되어있음.
					// 해결법. Map으로 담아서 하나로 만들어 전달을 해줌 
	}

	
// 230308 3교시
	// 게시글 수 조회(검색 기능 적용 2) 테스트
	public int getBoardCount(SqlSession session, String type, String keyword) {
			
		Map<String, String> map = new HashMap<>();
		// ㄴ<키, 값>					 ㄴ 앞에서 지정했기에 유추 할 수 있어서 <키 자료형, 값 자료형>생략 가능
		map.put("type", type);
		map.put("keyword", keyword);
			
		return session.selectOne("boardMapper.selectBoardCountByKeyWord2", map);
	}
		
// 230308 2교시 3교시(페이징 처리)
	// 게시글 목록 조회(검색 기능 적용2) 테스트
	public List<Board> findAll(SqlSession session, PageInfo pageInfo, String type, String keyword) {
		int limit = pageInfo.getListLimit();
		int offset = (pageInfo.getCurrentPage() - 1) * limit;		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		Map<String, String> map = new HashMap<>();
		// ㄴ<키, 값>					 ㄴ 앞에서 지정했기에 유추 할 수 있어서 <키 자료형, 값 자료형>생략 가능
		map.put("type", type);
		map.put("keyword", keyword);
		
		return session.selectList("boardMapper.selectAllByKeyWord2", map, rowBounds);
	}
	
	
// 230308 5교시
	// 게시글 수 조회(필터 기능 적용) 테스트 페이징처리 2페이지 출력해보기
	public int getBoardCount(SqlSession session, String[] filters) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("filters", filters);
		
		// 꼭 map이어야 하는 것 아님. findAll(SqlSession session, PageInfo pageInfo, String[] filters) 참고
//		return session.selectOne("boardMapper.selectBoardCountByFilters", map);
//		return session.selectOne("boardMapper.selectBoardCountByFilters", filters);
		return session.selectOne("boardMapper.selectBoardCountByFilters", Arrays.asList(filters));
		
	}	

// 230308 4교시 5교시(페이징 처리)
	// 게시글 목록 조회(필터 기능 적용) 테스트
	public List<Board> findAll(SqlSession session, PageInfo pageInfo, String[] filters) {
		int limit = pageInfo.getListLimit();
		int offset = (pageInfo.getCurrentPage() - 1) * limit;		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		/*
		 *  List나 Array 타입의 데이터를 파라미터로 전달하면, Mapper.xml에서는 list, array라는 이름으로 파라미터에 접근해야 한다.
		 *  만약, Mapper.xml에서 filters라는 이름으로 사용하고 싶으면 Map에 담아서 파라미터로 전달한다.
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("filters", filters);
	
		// map : 배열을 Map에 담아서 전달하면 selectAllByFilters의 <if> test="filters != null"
		return session.selectList("boardMapper.selectAllByFilters", map, rowBounds);
		// filters : 배열을 넘기면 selectAllByFilters의 <if> test="array != null"
//		return session.selectList("boardMapper.selectAllByFilters", filters, rowBounds);
		// Arrays.asList(filters) : 배열을 List로 만들어서 넘기면 selectAllByFilters의 <if> test="list != null"
//		return session.selectList("boardMapper.selectAllByFilters", Arrays.asList(filters), rowBounds);	
	}

	
// 230308 6교시 7교시
	// 게시글 상세 조회(댓글 포함) 테스트	
	public Board findBoardByNo(SqlSession session, int no) {
		return session.selectOne("boardMapper.selectBoardByNo", no);
	}
	
}
