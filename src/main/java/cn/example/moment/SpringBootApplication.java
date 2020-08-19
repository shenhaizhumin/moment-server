package cn.example.moment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

@MapperScan(basePackages = {"cn.example.moment.dao"})
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
        System.out.println("------------------启动成功------------------");
    }
}
