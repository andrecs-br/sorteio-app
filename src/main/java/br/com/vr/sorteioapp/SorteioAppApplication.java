package br.com.vr.sorteioapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SorteioAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SorteioAppApplication.class, args);
	}
}
