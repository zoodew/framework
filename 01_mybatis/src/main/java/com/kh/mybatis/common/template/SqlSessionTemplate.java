package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {

// 230228 7교시 정적 메소드 생성
	public static SqlSession getSession() {
		SqlSession session = null;
		SqlSessionFactoryBuilder builder = null;
		InputStream is = null;
		SqlSessionFactory factory = null;
		
		try {
			// 1. SqlSessionFactoryBuilder생성
			builder = new SqlSessionFactoryBuilder();
			
			// 마이바티스에서 자원을 좀 더 쉽게 로드할 수 있는 Resources 클래스를 제공한다.
			is = Resources.getResourceAsStream("mybatis-config.xml"); // ("") 읽어오고자 하는 파일명 적어주기
											// 커넥션 이런 서 설정파일에서 다 만들어줘서 여기서 굳이 만들 필요 없음
			
			// 2. SqlSessionFactory 오브젝트 생성
			factory = builder.build(is);
			
			// 3. SqlSession 오브젝트 생성 (true : 오토 커밋 false : 수동 커밋)
			session = factory.openSession(false);

		} catch (IOException e) {
			e.printStackTrace();
		} 

		return session;
	}
}
