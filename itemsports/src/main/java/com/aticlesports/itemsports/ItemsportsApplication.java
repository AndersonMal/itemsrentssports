package com.aticlesports.itemsports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.aticlesports.itemsports.mapper")
public class ItemsportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemsportsApplication.class, args);
	}

}
