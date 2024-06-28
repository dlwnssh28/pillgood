package com.pillgood;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PillgoodApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(PillgoodApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PillgoodApplication.class, args);
	}
	
	@Bean
    CommandLineRunner testDatabaseConnection(DataSource dataSource) {
        return args -> {
            try {
                dataSource.getConnection().close();
                logger.info("데이터베이스 연결 성공!");
            } catch (Exception e) {
                logger.error("데이터베이스 연결 실패: ", e);
            }
        };
    }
	
}
