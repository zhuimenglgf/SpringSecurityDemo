package com.zhuimeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhuimeng.dao")
public class ZhuimengProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhuimengProjectApplication.class, args);
	}
}
