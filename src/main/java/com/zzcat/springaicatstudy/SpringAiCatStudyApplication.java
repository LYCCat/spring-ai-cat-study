package com.zzcat.springaicatstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.zzcat.springaicatstudy.mapper")
public class SpringAiCatStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiCatStudyApplication.class, args);
    }


}
