package com.kennymaness.kennymaness;

import com.kennymaness.kennymaness.daos.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class KennymanessApplication {

	public static void main(String[] args) {
		SpringApplication.run(KennymanessApplication.class, args);
	}

}
