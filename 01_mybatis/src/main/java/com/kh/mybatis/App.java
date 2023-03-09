package com.kh.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

import static com.kh.mybatis.common.template.SqlSessionTemplate.getSession;;

/**
 * Hello world!
 *
 */
@Slf4j
public class App {
// 230309 5교시
	// 클래스 안에서 로그를 찍기 위해 사용하는 클래스
	// @Slf4j을 사용하면 log 선언 따로 할 필요 없다.
//	private static Logger log = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        log.info("Hello SLF4J");
        log.debug("Hello SLF4J - debug");
        // log4j2.xml의 Configuration status, Root level을 바꿔가면서(INFO, DEBUG..) ctrl f11 찍어서 어디에 찍히나 확인.

        
// 230228 6교시 230306 2교시
//        int count = 0;
//        
//        SqlSession session = getSession();
//        
//        count = session.selectOne("memberMapper.selectCount");
//        	// 수행시킬 쿼리문 namespace를 ""에 적어줌. member-mapper.xml에서 id가 memberMapper인 mapper태그의 id가 selectrCount인 쿼리문
//        
//        System.out.println("Count : " + count);
        
    }
}
