package com.ohgiraffers.section01;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Date;

public class Application {
    //db정보 필드 작성
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static String URL = "jdbc:mysql://localhost:3306/menudb";

    private static String USER = "gangnam";

    private static String PASS = "gangnam";

    public static void main(String[] args) {


        /*
        *                (환결설정)
        *   jdbcTransactionFactory  : 수동 커밋
        *   ManagedTransactionFactory :  오토 커밋
        * ------------------------------------------
        *   ConnectionPool : 커넥션 객체 미리 준비
        * PooledDateSource : ConnectionPool 을 사용함
        * UnPooledDateSource : 사용하지 않음
        * */

        //환경정보 저장 객체 아이디,트랜잭션 종류 , 풀 사용여부
        //환경 설정을 위한 Environment 객체를 생성합니다. 이 객체에는
        // 트랜잭션 관리 방식과 데이터베이스 연결 방식이 포함됩니다.
    Environment environment = new Environment(
            "dev",new JdbcTransactionFactory(),new PooledDataSource(DRIVER,URL,USER,PASS));

    //생성한 환경 설정 정보로 MyBatis 설정 객체 생성
        Configuration config = new Configuration(environment);

        //MyBatis의 Mapper 인터페이스를 추가하여 SQL 쿼리 매핑 설정을 추가합니다.
        config.addMapper(Mapper.class);

        /*
        * sqlSessionFactory : sqlSession 객체를 생성하기 위한 팩토리 역할의 인터페이스
        * sqlSessionFactoryBuilder :  sqlSessionFactory 타입의 객체를 생성하기 위한 빌드 역할
        * build() : 설정에 대한 정보를 담고 있는 configuration 타입의 객체 혹은 외부 설정
        * 파일과 연관된 데이터를 매개변수로 전달하면 sqlSessionFactory 타입의 객체를 반환하는 메소드
        *
        * sqlSession : jdbc의 connection 같은 객체
        * */



        //SqlSessionFactoryBuilder를 이용하여 SqlSessionFactory 객체를 생성합니다.
        // 이 객체는 SqlSession 인스턴스를 생성하여
        // 데이터베이스와의 세션을 시작하는 데 사용됩니다.
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        System.out.println(sqlSessionFactory);


        //false : 오토커밋 끔
        SqlSession session = sqlSessionFactory.openSession(false);

        Mapper mapper = session.getMapper(Mapper.class);
        Date date = mapper.selectSysDate();
        System.out.println(date);
        session.close();
    }
}
