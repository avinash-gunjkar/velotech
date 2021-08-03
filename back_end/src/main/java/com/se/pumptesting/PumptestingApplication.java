
package com.se.pumptesting;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.se.pumptesting")
@EntityScan(basePackages = { "com.se.pumptesting.models" })
@ServletComponentScan
@SpringBootApplication
public class PumptestingApplication {

	public static void main(String[] args) {

		SpringApplication.run(PumptestingApplication.class, args);
	}

	@Bean
	public SessionFactory sessionFactory(EntityManagerFactory factory) {

		if (factory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		return factory.unwrap(SessionFactory.class);
	}
}
