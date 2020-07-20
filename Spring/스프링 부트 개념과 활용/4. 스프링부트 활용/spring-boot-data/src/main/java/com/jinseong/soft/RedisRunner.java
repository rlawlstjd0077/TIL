package com.jinseong.soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

public class RedisRunner implements ApplicationRunner {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    AccountRepository accountRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("jinseong", "kim");
        values.set("sat", "reci");
        values.set("hello", "sat");

        Account account = new Account();
        account.setEmail("@com");
        account.setUsername("Kimsin");

        accountRepository.save(account);
        Optional<Account> result = accountRepository.findById(account.getId());
        System.out.println(result.get().getEmail());
    }
}
