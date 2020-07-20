package com.jinseong.soft;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4JRunner implements ApplicationRunner {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AccountNode accountNode = new AccountNode();
        accountNode.setUsername("fasfa");

        accountNode.setEmail("Asfsafa");

        Role role = new Role();
        role.setName("father");
        accountNode.getRole().add(role);

        Session session = sessionFactory.openSession();
        session.save(accountNode);
        sessionFactory.close();
    }
}
