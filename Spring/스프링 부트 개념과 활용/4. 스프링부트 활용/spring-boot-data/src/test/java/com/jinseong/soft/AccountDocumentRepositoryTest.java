package com.jinseong.soft;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AccountDocumentRepositoryTest {
//    @Autowired
//    AccountDocumentRepository repository;
//
//    @Test
//    public void test() {
//        AccountDocument accountDocument = new AccountDocument();
//        accountDocument.setUserName("kims");
//        accountDocument.setEmail("kims@asfas");
//
//        repository.save(accountDocument);
//        Optional<AccountDocument> document = repository.findById(accountDocument.getId());
//    }
}
