package com.oop2.tim6.NakitWebTim6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.oop2.tim6.NakitWebTim6.repository")
@EntityScan("com.oop2.tim6.NakitWebTim6.model")
@ComponentScan("com.oop2.tim6.NakitWebTim6.controller")
@SpringBootApplication
public class NakitWebTim6Application  {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NakitWebTim6Application.class, args);
    }


}