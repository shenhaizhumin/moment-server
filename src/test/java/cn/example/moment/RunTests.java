package cn.example.moment;

import cn.example.moment.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
public class RunTests {

    //    @Autowired
//    private UserMapper userMapper;
//
//    @Test
//    public void contextLoads(){}
//
//    @Test
//    private void G(){
//        System.out.println(userMapper.findAll());
//    }
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbc() {
        //{user=zengqi}
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user_info");
//        maps.forEach(System.out::println);
        for (int i = 0; i < maps.size(); i++) {
            System.out.println(maps.get(i));
        }
    }

//    @Autowired
//    DataSource dataSource;

    @Test
    public void test() {
        System.out.println("dataSource.getClass()");
    }
}
