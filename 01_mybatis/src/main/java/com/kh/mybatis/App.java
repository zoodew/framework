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
        
        int count = 0;
        
        SqlSession session = getSession();
        
        count = session.selectOne("memberMapper.selectCount");	// 수행시킬 쿼리문 namespace를 ""에 적어줌
        
        System.out.println("Count : " + count);
        
    }
}
