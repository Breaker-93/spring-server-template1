package com.breaker93.springservertemplate1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.breaker93.springservertemplate1.sys.mapper")
public class SpringServerTemplate1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringServerTemplate1Application.class, args);
    }

}
