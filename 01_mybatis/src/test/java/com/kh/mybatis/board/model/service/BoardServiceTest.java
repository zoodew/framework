package com.kh.mybatis.board.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

// 230307 4교시
@DisplayName("BoardService 테스트")
@TestMethodOrder(OrderAnnotation.class)	// OrderAnnotation : MethodOrderer 안에 있는 OrderAnnotation 선택!
class BoardServiceTest {
	private BoardService service;
	
	// @BeforeEach 테스트 메소드들을 실행 할 때마다 이 메소드 실행
	@BeforeEach
	void setUp() {
		service = new BoardService();
	}

	@Test
	@Disabled
	void nothing() {
	}	// 이 메소드가 잘 돌아가면 이 클래스가 테스트 하는 데 문제 없는 환경이라는 뜻. 테스트 하고 성공하면 @disabled 달아줌

	@Test
	@Disabled
	void create() {
		assertThat(service).isNotNull();
		// BoardService클래스에 아무 것도 없어도 create 테스트가 왜 성공을 하나요? isNotNull 테스트에서 실패가 떠야하는 거 아닌가?
		// 데이터가 없는 객체(인스턴스)를 선언해도 참조변수(service)에는 주소값이 들어있으므로 null이 아니다. 따라서 assertThat메서드를 수행해도 FAIL이 뜨지 않는다.
	}

	

// 230307 4교시	
	@Test
	@Order(1)
	@DisplayName("게시글 수 조회 테스트")
	void getBoardCountTest() {
		int count = 0;
		
		count = service.getBoardCount();
		
//		System.out.println(count);
		
		assertThat(count).isGreaterThan(0);
	}
	
// 230307 5교시 6교시
	@Test
	@Order(2)
	@DisplayName("게시글 목록 조회 테스트")
	void findAllTest() {
		List<Board> list = null;
		PageInfo pageInfo = null;
		int listCount = 0;

		listCount = service.getBoardCount();	// getBoardCountTest()를 통해 얻어온 값
//		System.out.println("게시글 목록 조회 테스트 listCount : " + listCount);
		pageInfo = new PageInfo(2, 10, listCount, 10);	// PageInfo에 커서 올려놓으면 매개값 설명 나옴
		list = service.findAll(pageInfo);	// 새로운 메소드를 생성하는 게 아니라 기존의 findAll 메소드에 pageInfo 파라미터 추가
		
//		System.out.println(list);			// list 값 다 가져옴 너무 많음!!!!!!!
//		System.out.println(list.get(0));	// list의 0번 인덱스 값 가져와라 list 다 가져오면 너무 많으니 하나만 확인
//		System.out.println(list.size());
		
		assertThat(list).isNotNull().isNotEmpty();
		assertThat(list.size()).isEqualTo(10);
	}
	
// 230307 8교시
	// 게시글 수 조회(검색 기능 적용) 테스트
	@ParameterizedTest
	@CsvSource(
			value = {
				"null, null, null",	// null인 모든 데이터 조회
				"ju, null, null",	// writer에 ad라는 문자열이 포함된 데이터만 조회
				"null, 50, null",	// title에 50이라는 문자열이 포함된 데이터만 조회
				"null, null, 10"	// content에 10이라는 문자열이 포함된 데이터만 조회
			},
			nullValues = "null"
		)
	@Order(3)
	@DisplayName("게시글 수 조회(검색 기능 적용) 테스트")
	void getBoardCountTest(String writer, String title, String content) {
		int count = 0;
		
		count = service.getBoardCount(writer, title, content);
		
//		System.out.println(count);
		// writer, title, content 셋 다 null인 count 출력
		// writer에 ju가 포함된 count 출력
		// title에 50가 포함된 count 출력
		// content에 10가 포함된 count 출력
		// 총 4줄
		
		assertThat(count).isGreaterThan(0);
	}
	
// 230307 7교시
	// 게시글 목록 조회(검색 기능 적용) 테스트
	// 동적 쿼리 사용
	@ParameterizedTest
	@CsvSource(
		value = {
			"null, null, null",	// null인 모든 데이터 조회
			"ju, null, null",	// writer에 ju라는 문자열이 포함된 데이터만 조회
			"null, 50, null",	// title에 50이라는 문자열이 포함된 데이터만 조회
			"null, null, 10"	// content에 10이라는 문자열이 포함된 데이터만 조회
		},
		nullValues = "null"
	)
	@Order(4)
	@DisplayName("게시글 목록 조회(검색 기능 적용) 테스트")
	void findAllTest(String writer, String title, String content) {
	// ㄴ 동일한 이름(findAllTest())이어도 메소드 오버로딩에 의해 추가로 동일한 이름의 메소드 생성 가능함
		
		// @CsvSource({"null, null, null"})를 하고 아래 assetThat 테스트 > 실패! 왜?? "null, null, null"은 비어있는 게 아니라 null이라는 문자열이 들어가 있는 것
		// @CsvSource(value = {"null, null, null"}, nullValues = "null") 이게 진짜 비어있는 null. 의미 테스트 성공
//		assertThat(writer).isNull();
//		assertThat(title).isNull();
//		assertThat(content).isNull();
		
		// @CsvSource에 추가 후 콘솔로 찍어서 확인
//		System.out.println(writer);
//		System.out.println(title);
//		System.out.println(content);
//		System.out.println();
		
		List<Board> list = null;
// 230308 1교시
		// 페이징 처리
		PageInfo pageInfo = null;
		int listCount = 0;
		
		listCount = service.getBoardCount(writer, title, content);	// 주의! getBoardCount() 두 개임 검색기능 관한 메소드 사용!
		pageInfo = new PageInfo(1, 10, listCount, 10);
		list = service.findAll(pageInfo, writer, title, content);	// 주의! change Method할 때 검색기능 포함한 메소드를 바꿔야함!
																	// findAll(String, String, String);
		
		// 검색 기능 적용해 게시글 조회가 되나 확인
//		System.out.println(list);
//		System.out.println(list.size());
			// writer, title, content 셋 다 null인 list, list.size 출력
			// writer에 ju가 포함된 list, list.size 출력
			// title에 50가 포함된 list, list.size 출력
			// content에 10가 포함된 list, list.size 출력
			// 총 8줄
		
			// 페이징 처리 됐는지 확인
		
		
		assertThat(list).isNotNull().isNotEmpty();
	}
	
	
//	230308 3교시
	// 검색 type(작성자, 제목, 내용) 검색 keyword를 값으로 받는 검색 기능 구현 페이징 처리 위한 count 수 구하기
	@ParameterizedTest
	@CsvSource(
			value = {
					"null, null",
					"writer, ju",
					"title, 50",
					"content, 10"
			}
		)
	@Order(5)
	@DisplayName("게시글 수 조회(검색 기능 적용 2) 테스트")
	void getBoardCountTest(String type, String keyword) {
		int count = 0;
		
		count = service.getBoardCount(type, keyword);
		
//		System.out.println(count);
		// writer, title, content 셋 다 null인 count 출력
		// writer에 ju가 포함된 count 출력
		// title에 50가 포함된 count 출력
		// content에 10가 포함된 count 출력
		// 총 4줄
		
		assertThat(count).isGreaterThan(0);
	}
	
//	230308 2교시 3교시(페이징 처리)
	// 검색 type(작성자, 제목, 내용) 검색 keyword를 값으로 받는 검색 기능 구현
	@ParameterizedTest
	@CsvSource(
		value = {
			"null, null",
			"writer, ju",
			"title, 50",
			"content, 10"
		}
	)
	@Order(6)
	@DisplayName("게시글 목록 조회(검색 기능 적용 2) 테스트")
	void findAllTest(String type, String keyword) {
		// @CsvSource의 값이 잘 들어갔는지 확인하는 코드
//		System.out.println("테스트 전 type 값 : " + type);
//		System.out.println("테스트 전 keyWord 값 : " + keyWord);
		
		List<Board> list = null;		
		// 페이징 처리
		PageInfo pageInfo = null;
		int listCount = 0;
				
		listCount = service.getBoardCount(type, keyword);	// 주의! getBoardCount() 여러 개임 관련 메소드 사용!
		pageInfo = new PageInfo(1, 10, listCount, 10);
		list = service.findAll(pageInfo, type, keyword);	// 주의! change Method할 때 관련 메소드를 바꿔야 함!
																			// findAll(String, String);
//		System.out.println(list);
//		System.out.println(list.size());
		
		assertThat(list).isNotNull().isNotEmpty();
	}
	

// 230308 5교시
	// 게시글 수 조회(필터 기능 적용) 테스트 페이징처리 2페이지 출력해보기
	@ParameterizedTest
	@MethodSource("filterProvider")
	@Order(7)
	@DisplayName("게시글 수 조회(필터 기능 적용) 테스트")
	void getBoardCountTest(String[] filters) {
		// 배열 잘 가져오나 조회
//		System.out.println(Arrays.toString(filters));		// Arrays~ 안 해주고 그냥 filters syso 찍으면 filters의 주소값 나옴
		
		int count = 0;

		count = service.getBoardCount(filters);
		
//		System.out.println("필터 기능 적용 게시글 count : " + count);
		
		assertThat(count).isGreaterThan(0);
	}	
	
// 230308 4교시
	// 게시글 목록 조회(필터 기능 적용) 테스트
	@ParameterizedTest
	@MethodSource("filterProvider")	// @MethodSource("filterProvider()메소드가 리턴한 값을 갖고 테스트 메소드(findAllTest(String[] filters))의 파라미터를 만들겠다")
	@Order(8)						// 	ㄴ 테스트 메소드에 문자열을 쓰면 이 어노테이션(@MethodSource) 사용
	@DisplayName("게시글 목록 조회(필터 기능 적용) 테스트")
	void findAllTest(String[] filters) {	// 웹에서 체크박스 여러 개 선택해 넘기면 그 값이 배열로 넘어옴. 이걸 가정하에 하는 거라 테스트 메소드에 문자열 받음
//		System.out.println(filters);	// 주소값 찍힘
//		System.out.println(Arrays.toString(filters));	// filters 값 
		
		List<Board> list = null;
		// 페이징 처리
		PageInfo pageInfo = null;
		int listCount = 0;
		
		listCount = service.getBoardCount(filters);	// 주의! getBoardCount() 여러 개임 관련 메소드 사용!
		pageInfo = new PageInfo(2, 10, listCount, 10);
		list = service.findAll(pageInfo, filters);
		
//		System.out.println(list);
//		System.out.println(list.size());
		
		assertThat(list).isNotNull().isNotEmpty();
	}
	
	
// 230308 6교시
	// 게시글 상세 조회(댓글 포함) 테스트	
	@Test
	@Order(9)
	@DisplayName("게시글 상세 조회(댓글 포함) 테스트")
	void findBoardByNoTest() {
		Board board = null;	// Board 타입의 참조변수 board
		
		board = service.getBoardByNo(122);	// 122번 게시글에 댓글이 있어서 122번 게시글 가져옴
		
		System.out.println(board);
		System.out.println(board.getReplies());
		
		assertThat(board).isNotNull();
		assertThat(board.getNo()).isEqualTo(122);
	}
	
	
	
	
	
	
	
	
	
	
// 230308 4교시
	// 게시글 목록 조회(필터 기능 적용) 테스트	
	// 정적 메소드(public static)
	public static Stream<Arguments> filterProvider() {	// 위 findAllTest(String[] filters)메소드의 @MethodSource
		return Stream.of(
			Arguments.arguments((Object) new String[] {"B2", "B3"}),	// 테스트 메소드(findAllTest(String[] filters)) 파라미터에 넣고 싶은 값을 배열로 넣어줌. B2, B3 를 가져옴
			Arguments.arguments((Object) new String[] {"B3"})			// B3 만 선택하는 경우
		);
	}
	
}
