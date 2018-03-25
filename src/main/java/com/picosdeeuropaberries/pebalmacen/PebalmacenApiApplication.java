package com.picosdeeuropaberries.pebalmacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PebalmacenApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PebalmacenApiApplication.class, args);
	}
}
