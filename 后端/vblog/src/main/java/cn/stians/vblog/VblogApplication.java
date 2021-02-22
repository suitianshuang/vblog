package cn.stians.vblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "cn.stians.vblog.mapper")
@ServletComponentScan
@EnableScheduling
public class VblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(VblogApplication.class, args);
    }

}
