package org.example.astonlearn;

import org.springframework.boot.SpringApplication;

public class TestAstonLearnApplication {

    public static void main(String[] args) {
        SpringApplication.from(AstonLearnApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
