package com.rest.payment;

public class CardPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Processing card payment of Rs: " + String.format("%.2f", amount));
    }

    @Override
    public String getMethodName() {
        return "Card";
    }
}
