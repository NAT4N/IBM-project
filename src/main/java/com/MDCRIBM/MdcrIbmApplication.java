package com.MDCRIBM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MdcrIbmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdcrIbmApplication.class, args);
	}

}
