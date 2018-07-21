package org.jlmartinez.test;

import org.jlmartinez.test.config.DataSourceConfig;
import org.jlmartinez.test.config.HttpConnectorConfig;
import org.jlmartinez.test.config.Swagger2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ DataSourceConfig.class, HttpConnectorConfig.class, Swagger2Config.class })
public class JlmartinezTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JlmartinezTestApplication.class, args);
	}
}
