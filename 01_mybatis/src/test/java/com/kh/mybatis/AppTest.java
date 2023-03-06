package com.kh.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.kh.mybatis.common.template.SqlSessionTemplate;

// 230306 2교시

//	테스트 클래스
@DisplayName("첫 번째 테스트 코드 작성")
public class AppTest 
{
	
//	테스트 메소드 하나의 단위 테스트 용도로 사용

//	230306 3교시	
	@Test
    @Disabled	// @Disabled : 테스트 메소드를 비활성 하는 어노테이션
    public void nothing() {
    	// 아무 것도 없는 이 테스트를 통해 현재 프로젝트에서 테스트가 가능한지 확인한다.   	
    }

// 230306 2교시
//    public void shouldAnswerWithTrue()
//    {
//    	assertTrue( true );		// assertTrue() 테스트 수행 결과를 판단할 때 사용하는 메소드
//    	// 테스트 메소드 실행 > 메소드 우클릭 Run As > JUnit Test
//    }

// 230306 3교시
	@Test
	@DisplayName("SqlSession 생성 테스트")	// run as 했을 때 메소드 명이 아니라 지정한 이름으로 나옴
	public void create() {
		SqlSession session = SqlSessionTemplate.getSession();

		// 생성 후 콘솔로 찍어서 성공 실패를 눈으로 직접 확인하는 법
		//System.out.println(session);

		// 테스트 메소드 자체에서 성공했는지 실패했는지 메소드를 통해 판별
		assertNotNull(session);
		assertEquals(10, 10);	// (예상값, 실제값)
		
		org.assertj.core.api.Assertions.assertThat(10).isEqualTo(10);
						// assertThat(실제값).isEqualtTo(예상값); 같으면 실행 성공, 틀리면 실행 실패
	}
}