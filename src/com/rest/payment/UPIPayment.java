package com.rest.payment;

public class UPIPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Processing UPI payment of Rs: " + String.format("%.2f", amount));
    }

    @Override
    public String getMethodName() {
        return "UPI";
    }
}
