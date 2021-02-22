package cn.stians.vblogadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "cn.stians.vblogadmin.mapper")
@SpringBootApplication
public class VblogadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(VblogadminApplication.class, args);
    }

}
