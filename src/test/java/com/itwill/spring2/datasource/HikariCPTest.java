package com.itwill.spring2.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}
        )
// 스프링 컨텍스트 환경 설정 파일의 경로와 이름.
// 설정 내용들을 xml 내용을 알려주는 거임.
public class HikariCPTest {

    // xml 설정 빈에 있는 친구들은 이미 생성되어 있다. <-- 메모리에 있다는 말.
    // 그래서 변수만 선언해도 생성자가 실행된 객체가 있다.
    // 객체를 별도로 생성하거나 참조하지 않아도 된다. 
    // 컨테이너 톰캣이 실행 -> 스프링 앱이 실행 :: 스프링앱을 컨테이너라고 한다
    // 컨테이너라고 하는 이유는 : 객체를 담고 있는 창고라고 생각했기 때문
    // 이와 연결되는 것은 bean으로 생성된 객체를 스프링이 다 가지고 있다는 의미이다.
    // @Autowired라는 어노테이션으로 그 객체를 사용할 수 있다.(주입을 해준다.)<- dependency action 이라고 불리우며 "의존성 주입" 이라고 한다.
    // 객체를 사용하는 곳에서 수동적으로 이용한다. 생성하는 곳에서 권한을 가지고 있다고 봐도 된다. 이를 제어의 역전(Inversion of control) : 제어의 역전
    
//    @Autowired
//    @Qualifier("hikariConfig")// - 다 주입이라 몰름 게다가 상속 관계라 헷갈림 엄마쪽에 이거 해야 정상 됨
//    private HikariConfig config;
    
    @Autowired
    private HikariDataSource ds;
    
    @Autowired
    private SqlSessionFactoryBean sessionFactory;
    
    @Test
    public void testSqlSession() {
        Assertions.assertNotNull(sessionFactory);
        log.info("session = {}", sessionFactory);
    }
    
    
    /* public class HikariDataSource extends HikariConfig */
    @Test
    public void testDataSource() throws SQLException {
        Assertions.assertNotNull(ds);
        
        log.info("ds = {}", ds);
        
        Connection conn = ds.getConnection(); // Data Source에서 Connection을 빌려옴.
        Assertions.assertNotNull(conn);
        log.info("conn = {}", conn);
        
        conn.close(); // 사용했던 Connection을 Data Source에 반환.
        log.info("conn close 성공");
        
    }
    
}
