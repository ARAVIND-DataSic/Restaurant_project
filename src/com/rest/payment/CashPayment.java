package com.rest.payment;

public class CashPayment implements PaymentMethod {

    @Override 
    public void pay(double amount) {
        System.out.println("Processing cash payment of Rs: "+ String.format("%.2f", amount));
    }

    @Override public String getMethodName() {
        return "Cash";
    }
    
}
