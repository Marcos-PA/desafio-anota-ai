package com.marcps.desafioanotaai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories // This annotation is necessary to enable the use of MongoRepository
public class DesafioAnotaAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioAnotaAiApplication.class, args);
    }

}
