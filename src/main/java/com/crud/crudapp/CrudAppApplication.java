package com.crud.crudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@ComponentScan({"com.crud.crudapp.router",
        "com.crud.crudapp.handler",
        "com.crud.crudapp.service",
        "com.crud.crudapp.repository"})
@EntityScan("com.crud.crudapp.entity")
@EnableR2dbcRepositories("com.crud.crudapp.repository")
public class CrudAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudAppApplication.class, args);
    }

}
