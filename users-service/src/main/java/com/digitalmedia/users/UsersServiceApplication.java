package com.digitalmedia.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
public class UsersServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UsersServiceApplication.class, args);
  }

}
