package com.cg.pms.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotpProjectPmsApplication {

	public static void main(String[] args) {
		AccompanyingPerson a1=new AccompanyingPerson();
		System.out.println(a1);
		SpringApplication.run(BotpProjectPmsApplication.class, args);
	}

}
