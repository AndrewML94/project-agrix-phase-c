package com.betrybe.agrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
* Application main class.
*/
@SpringBootApplication
@EntityScan(basePackages = { "com.betrybe.agrix.ebytr.staff.entity",
    "com.betrybe.agrix.models.entities" })
@EnableJpaRepositories(basePackages = { "com.betrybe.agrix.ebytr.staff.repository",
    "com.betrybe.agrix.models.repositories" })
public class AgrixApplication {

  public static void main(String[] args) {
    SpringApplication.run(AgrixApplication.class, args);
  }

}
