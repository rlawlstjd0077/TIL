package org.springframework.samples.petclinic.proxy;

public class Cash implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("현금 결제");
    }
}
