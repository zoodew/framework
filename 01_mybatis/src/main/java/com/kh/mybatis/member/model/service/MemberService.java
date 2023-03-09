package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

import static com.kh.mybatis.common.template.SqlSessionTemplate.getSession;

import java.util.List;

// 230306 3, 4교시
@Slf4j		// 230309 6교시
public class MemberService {
	// 클래스명에 커서 놓고 ctrl 1 creat junit test어쩌고 동일한 패키지 구조로 test 폴더에 테스트 클래스 생성


// 230306 5교시
	// 멤버수 조회
	public int getMemberCount() {
		int count = 0;
		SqlSession session = getSession();	// import 에 static getSession하고 SqlSessionTemplate.getSession()에서 SqlSessionTemplate. 생략

		count = new MemberDao().getMemberCount(session);
		
		log.info("getMemberCount() 메소드 호출");
		log.debug("getMemberCount() 메소드 호출 - " + count);
		
		// connection 등도 사용 후 close를 해 준 것처럼 session도 반환해줌
		session.close();
		
		// myBatis에서는 mvc의 connection대신 SqlSession사용한다고 생각하기
		
		return count;
	}
	
	// 모든 회원 조회
	public List<Member> findAll() {
		List<Member> members = null;
		SqlSession session = getSession();
		
		members = new MemberDao().findAll(session);
		
		session.close();
		
		return members;
	}
	
// 230306 7교시
	// ID를 통해 멤버 조회
	public Member findMemberById(String id) {
		Member member = null;
		SqlSession session = getSession();
		
		member = new MemberDao().findMemberById(session, id);
		
		session.close();
		
		return member;
	}

// 230307 1교시
	// 회원 등록 테스트
	public int save(Member member) {
		int result = 0;
		
		// mvc에서 connection을 얻어오듯 SqlSession object를 얻어옴
		SqlSession session = getSession();
		
		if(member.getNo() > 0) {	// no는 PK값이라 유일함 no 받아서 0보다 크면 ~
			result = new MemberDao().updateMember(session, member);
		} else {
			result = new MemberDao().insertMember(session, member);			
		}
		
//		System.out.println(member);
//		System.out.println(result);
		
		// 영향 받은 행이 있으면(insert든 update든) 커밋, 아니면 롤백
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}
	
// 230307 3교시	
	// 회원 삭제 테스트
	public int delete(String id) {
		int result = 0;
		SqlSession session = getSession();
		
		result = new MemberDao().delete(session, id);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}
}

