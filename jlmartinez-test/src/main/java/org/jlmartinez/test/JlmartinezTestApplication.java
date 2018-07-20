package org.jlmartinez.test;

import org.jlmartinez.test.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ DataSourceConfig.class })
public class JlmartinezTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JlmartinezTestApplication.class, args);
	}
}
