package com.kh.mybatis.member.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.member.model.vo.Member;

// 230306 4교시
@DisplayName("MemberService 테스트")
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
	@DisplayName("회원 수 조회 테스트")
	void getMemberCountTest() {	// getMemberCount라는 메소드를 테스트 할 수 있는 메소드 
		int count = 0;
		
		count = service.getMemberCount();
		
//		System.out.println(count);
		
//		assertThat(count).isGreaterThan(0);		// isGreaterThan() : count의 값이 매개값으로 전달된 값보다 크면 테스트 성공, 크지 않으면 실패	
		assertThat(count).isPositive().isGreaterThanOrEqualTo(2);	// 양수냐 | 2보다 크거나 같냐
	}
	
	@Test
	@DisplayName("모든 회원 조회 테스트")
	void findAllTest() {
		List<Member> members = null;
		
		members = service.findAll();
		
		// members에 모든 행의 값이 잘 담겼는지 콘솔에 찍어서 눈으로 직접 보기
//		System.out.println(members);
		// enrollDate, modifyDate 값이 null로 나옴 why? DB의 컬럼명과 vo의 필드명이 달라서. member-mapper.xml에 자세히 적혀있는 것 참고하기 
		// 값을 넣어주는 법 1) 컬럼명에 별칭을 줘서 컬럼명과 필드명을 동일하게 만들어 준다.
		// 값을 넣어주는 법 2) resultMap을 이용해서 명시적으로 컬럼명과 필드명을 mapping 시켜준다.
		
		assertThat(members).isNotNull().isNotEmpty();
		// members가 정상적으로 조회된다면 List가 null도 아니고 비어있지도 않을 것.
	}
	
// 230306 7교시
	//@Test
	@ParameterizedTest		// 테스트를 할 때 값을 넘겨줄 수 있는 어노테이션
	@ValueSource(strings = {"admin2", "ismoon"}) // strings에 문자열의 배열 형태로 테스트할 값 넣어줌  
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
		
	}

	
}
