package com.satendra.jwtcsrf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JWT stateless application the jwt token attached to response header
 */
@SpringBootApplication
public class JwtCsrfApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtCsrfApplication.class, args);
	}

}
