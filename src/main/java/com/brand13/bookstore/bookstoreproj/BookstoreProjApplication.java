package com.brand13.bookstore.bookstoreproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookstoreProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreProjApplication.class, args);
	}

}
