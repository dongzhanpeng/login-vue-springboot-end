package com.dzp.login.loginsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LoginsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginsystemApplication.class, args);
    }

}
