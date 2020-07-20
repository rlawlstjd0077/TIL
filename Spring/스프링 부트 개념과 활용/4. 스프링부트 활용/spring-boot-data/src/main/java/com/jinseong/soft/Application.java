package com.jinseong.soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class Application {

//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @Autowired
//    AccountDocumentRepository repository;

//    @Bean
//    public ApplicationRunner applicationRunner() {
//        return args -> {
//            AccountDocument accountDocument = new AccountDocument();
//            accountDocument.setUserName("lee");
//            accountDocument.setEmail("lee@sagte");
//            //mongoTemplate.insert(accountDocument);
//
//            repository.insert(accountDocument);
//
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
