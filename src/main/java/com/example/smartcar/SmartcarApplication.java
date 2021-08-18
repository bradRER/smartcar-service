package com.example.smartcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//public class SmartcarApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SmartcarApplication.class, args);
//	}
//
//}


@EnableJpaAuditing
@SpringBootApplication
public class SmartcarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SmartcarApplication.class);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SmartcarApplication.class);
	}
}
