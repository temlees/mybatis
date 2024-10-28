package com.ohgiraffers.sectioin02;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Application {
    public static void main(String[] args) {
        // mybatis는 기본적으로 resources 폴더를
        //기준으로 XML 파일을 찾을 수 있다
        String resource = "mybatis-config.xml";

        SqlSession session = null;

        try {
            //xml 파일 정보를 읽는 객체 getResourceAsStream
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session  = sqlSessionFactory.openSession(false);

            //결과 쿼리가 한줄일때 selectOne
            //여러줄이면 selectList로 받는다
            Date date = session.selectOne("mapper.selectSysDate");
            System.out.println(date);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

    }
}
