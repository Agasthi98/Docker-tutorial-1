package com.spring.dockerturorial1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class DockerTurorial1Application {

    public static void main(String[] args) {
        SpringApplication.run(DockerTurorial1Application.class, args);
    }

}
