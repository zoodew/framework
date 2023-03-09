package com.kh.mybatis.member.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

// 230306 4교시
@DisplayName("MemberService 테스트")
@TestMethodOrder(OrderAnnotation.class)	// 230307 1교시 테스트 메소드 순서 제어 @TestMethodOrder OrderAnnotation에 class 정보 넘겨주고 메소드에 @order 달아서 순서 지정해줌
class MemberServiceTest {
	
	private MemberService service;

	// @BeforeAll  모든 테스트 메소드가 실행되기 전에 딱 한 번만 실행
	// @BeforeEach 각각의 테스트 메소드가 실행되기 전에 한 번씩 실행
	@BeforeEach		// @BeforeEach : 이 어노테이션이 붙은 메소드를 각각의 테스트 메소드가 실행되기 전에 먼저 실행시켜주는 어노테이션
	void setUp() {
		service = new MemberService();
	}
	
	@Test
	@Disabled
	void nothing() {		
	}
	
	@Test
	@Disabled
	void create() {
		// 매개값으로 전달된 값(service)이 null인지 아닌지 테스트
		assertThat(service).isNotNull();
	}

	
// 230306 5교시
	@Test
	@Order(1)		// 230307 1교시 테스트 메소드 순서 제어
	@DisplayName("회원 수 조회 테스트")
	void getMemberCountTest() {	// getMemberCount라는 메소드를 테스트 할 수 있는 메소드 
		int count = 0;
		
		count = service.getMemberCount();
		
		// 눈으로 검증
//		System.out.println(count);
		
//		assertThat(count).isGreaterThan(0);		// isGreaterThan() : count의 값이 매개값으로 전달된 값보다 크면 테스트 성공, 크지 않으면 실패	
		assertThat(count).isPositive().isGreaterThanOrEqualTo(2);	// 양수냐 | 2보다 크거나 같냐
	}
	
	
	@Test
	@Order(2)		// 230307 1교시 테스트 메소드 순서 제어
	@DisplayName("모든 회원 조회 테스트")
	void findAllTest() {
		List<Member> members = null;
		
		members = service.findAll();
		
		// members에 모든 행의 값이 잘 담겼는지 콘솔에 찍어서 눈으로 직접 보기
//		System.out.println(members);
		// enrollDate, modifyDate 값이 null로 나옴 why? DB의 컬럼명과 vo의 필드명이 달라서. member-mapper.xml에 자세히 적혀있는 것 참고하기 
		// 값을 넣어주는 법 1) 컬럼명에 별칭을 줘서 컬럼명과 필드명을 동일하게 만들어 준다.
		// 값을 넣어주는 법 2) resultMap을 이용해서 명시적으로 컬럼명과 필드명을 mapping 시켜준다.
		
		assertThat(members).isNotNull().isNotEmpty().extracting("id").contains("admin2");
		// members가 정상적으로 조회된다면 List가 null도 아니고 비어있지도 않을 것."id"를 뽑아내 "admin2"가 있는지 확인
	}
	
	
// 230306 7교시
	//@Test
	@ParameterizedTest		// 테스트를 할 때 값을 넘겨줄 수 있는 어노테이션
	@Order(3)				// 230307 1교시 테스트 메소드 순서 제어
	@ValueSource(strings = {"admin2", "dljuhee"}) // strings에 문자열의 배열 형태로 테스트할 값 넣어줌  
	@DisplayName("회원 조회 테스트(ID로 검색)")
	void findMemberByIdTest(String id) {
		Member member = null;
		
		//member = service.findMemberById("admin2");

// 230306 8교시
		//테스트 할 때마다 값을 바꾸지 않고 특정 값을 지정해 여러 번 테스트 할 수 있게 만들기
		// @ParameterizedTest @ValueSource 함께 보기
		member = service.findMemberById(id);
		
//		System.out.println(member);
		
		assertThat(member).isNotNull();
		assertThat(member.getId()).isNotNull().isEqualTo(id);
		
	}


// 230307 1교시 2교시(@ParameterizedTest)
//	@Test
	@ParameterizedTest			// @ValueSource = 하나의 매개값에 값을 넣을 때 사용 | @CsvSource = 여러 개의 매개값에 값을 넣을 때 사용
	@CsvSource(value = {"test1, 1234, 홍길동", "test2, 4567, 임꺽정"})	// CSV(Comma Seperated Value) 데이터를 콤마(,)로 구분하는 포맷
	@Order(4)
	@DisplayName("회원 등록 테스트")			// r 매개값 @ParameterizedTest 설정 후 지정. @test로 쓸 때는 매개값 없음
	void insertMemberTest(String id, String password, String name) {
		int result = 0;
//		Member member = new Member("test1", "1234", "홍길동");	// Member.java에 생성자 생성한 거 사용

		// 여러 값 지정 후 테스트 @ParameterizedTest @CsvSource
		Member member = new Member(id, password, name);
		
		// System.out.println("인서트 멤버 : " + member);
		
		result = service.save(member);	// 웹이 없어서 값을 받아올 수 없기 때문에 위에서 member 값을 받아와줌.
		
		assertThat(result).isGreaterThan(0);	// 영향받은 행의 개수가 0보다 큰지
		assertThat(member.getNo()).isGreaterThan(0);
		
		// 위에서 만든 회원 조회 테스트 메소드 사용
//		assertThat(service.findMemberById("test1")).isNotNull();
		// 여러 값 지정 후 테스트 @ParameterizedTest @CsvSource
		assertThat(service.findMemberById(id)).isNotNull();
	}
	

// 230307 2교시 3교시(@ParameterizedTest)
//	@Test
	@ParameterizedTest
	@CsvSource({
		"test1, 0000, 이몽룡",
		"test2, 9999, 변사또"
	})	// values없이 바로 중괄호 사용해도 됨 위의 insertMemberTest 메소드와 비교
	@DisplayName("회원 정보 수정 테스트")
	@Order(5)
	void updateMemberTest(String id, String password, String name) {
		int result = 0;

// 230307 2교시	
		// 아이디로 회원 조회
//		Member member = service.findMemberById("test1");
		
		// 그 멤버의 데이터 수정 후 save 메소드 호출
//		member.setPassword("0000");
//		member.setName("이몽룡");

		
// 230307 3교시 @ParameterizedTest
		// 아이디로 회원 조회
		Member member = service.findMemberById(id);
		
		// 그 멤버의 필드값 수정 후 save 메소드 호출
		member.setPassword(password);
		member.setName(name);
		
//		System.out.println("업데이트 멤버 : " + member);
		
		result = service.save(member);	// 영향받은 행의 개수 result에 담아줌
		
		assertThat(result).isGreaterThan(0);
		assertThat(service.findMemberById(id).getName()).isEqualTo(name);
	}
	
	
// 230307 3교시
//	@Test
	@ParameterizedTest
	@ValueSource(strings = {"test1", "test2"})
	@DisplayName("회원 삭제 테스트")
	@Order(6)
	void deletdTest(String id) {
		int result = 0;

// @Test용
		// 아이디(unique 제약조건 걸린 컬럼 = 유일)를 조회해서 회원 삭제
//		result = service.delete("test1");	// delete 메소드 수행하면 영향 받은 행의 개수 리턴. 그 개수를 result에 담아줌

// @ParameterizedTest용 , 테스트 메소드 매개값 String id 주기 테스트 클래스 전체 돌리기
		result = service.delete(id);
		
		assertThat(result).isEqualTo(1);	// id는 unique 제약 조건이 걸려 있어서 id로 조회해서 삭제하면 하나 이상 삭제가 불가능하다.
											// 그래서 삭제하면 영향 받은 행의 개수 1이 나옴
		assertThat(service.findMemberById(id)).isNull(); // 실제 DB에서 삭제 됐는지 확인하는 테스트. DB에서 id 조회 후 null이면 테스트 성공
	}
	
	
}
