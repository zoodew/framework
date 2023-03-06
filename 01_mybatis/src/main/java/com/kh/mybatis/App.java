package com.kh.mybatis;

import org.apache.ibatis.session.SqlSession;

import static com.kh.mybatis.common.template.SqlSessionTemplate.getSession;;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

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
