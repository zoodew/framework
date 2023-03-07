package com.kh.mybatis.board.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
		
		System.out.println(count);
		
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
		pageInfo = new PageInfo(2, 10, listCount, 10);	// PageInfo에 커서 올려놓으면 매개값 설명 나옴
		list = service.findAll(pageInfo);	// 새로운 메소드를 생성하는 게 아니라 기존의 findAll 메소드에 pageInfo 파라미터 추가
		
//		System.out.println(list);			// list 값 다 가져옴
		System.out.println(list.get(0));	// list의 0번 인덱스 값 가져와라 list 다 가져오면 너무 많으니 하나만 확인
		System.out.println(list.size());
		
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
		
		System.out.println(count);
		// writer, title, content 셋 다 null인 count 출력
		// writer에 ju가 포함된 count 출력
		// title에 50가 포함된 count 출력
		// content에 10가 포함된 count 출력
		// 총 4줄
		
		assertThat(count).isGreaterThan(0);
	}
	
// 230307 7교시
	// 게시글 목록 조회(검색 기능 적용) 테스트
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
		
		list = service.findAll(writer, title, content);
		
		// 검색 기능 적용해 게시글 조회가 되나 확인
		System.out.println(list);
		System.out.println(list.size());
			// writer, title, content 셋 다 null인 list, list.size 출력
			// writer에 ju가 포함된 list, list.size 출력
			// title에 50가 포함된 list, list.size 출력
			// content에 10가 포함된 list, list.size 출력
			// 총 8줄
		
		assertThat(list).isNotNull().isNotEmpty();
	}
	
	
}
