package com.kh.mybatis.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {

// 230306 5교시
	// 멤버수 조회
	public int getMemberCount(SqlSession session) {
		
		return session.selectOne("memberMapper.selectCount");
		// 한 개의 값을 조회하기 위해 selectOne 호출. 매개값으로 mapper.xml의 쿼리문.
	}

// 230306 6교시
	// 모든 회원 조회
	public List<Member> findAll(SqlSession session) {
		
		return session.selectList("memberMapper.selectAll");
		// 한 개의 값만 조회하는 게 아니라 List를 조회하기 때문에 selectList
	}

// 230306 7교시
	// ID를 통해 멤버 조회
	public Member findMemberById(SqlSession session, String id) {
			
		return session.selectOne("memberMapper.selectMemberById", id);
		// ID가 조건에 준 ID와 동일한 값 하나만 받아와서 selectOne
		// 매개값에 id 주면 원하는 곳에 id값을 전달해 줄 수 있음. > memberMapper의 selectMemberById의 #{id}
	}

// 230307 1교시 2교시
	// 회원 등록 테스트 INSERT
	public int insertMember(SqlSession session, Member member) {
											// ㄴ 매개값으로 member 정보 받아와서 r 받아온 정보 넣어줌
		return session.insert("memberMapper.insertMember", member);

	}

// 230307 3교시
	// 회원 등록 테스트 UPDATE
	public int updateMember(SqlSession session, Member member) {
		
		return session.update("memberMapper.updateMember", member);
	}
	
// 230307 3교시
	// 회원 삭제 테스트
	public int delete(SqlSession session, String id) {

		return session.delete("memberMapper.deleteMember", id);
	}

	
}
