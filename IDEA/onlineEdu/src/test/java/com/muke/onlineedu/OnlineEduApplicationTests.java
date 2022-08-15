package com.muke.onlineedu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
public
class OnlineEduApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    void contextLoads() {
    }
    @Test
    void test1() throws SQLException {
        System.out.println(">>>:"+dataSource.getConnection());

        String sql="select curdate() from dual";
        String res=jdbcTemplate.queryForObject(sql,String.class);
        System.out.println("res:"+res);
    }

}
