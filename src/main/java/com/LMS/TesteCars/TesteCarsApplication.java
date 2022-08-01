package com.LMS.TesteCars;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TesteCarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteCarsApplication.class, args);
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("queue");
	}
}
