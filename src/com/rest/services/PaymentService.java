package com.rest.services;

import com.rest.models.Payment;
import com.rest.payment.PaymentMethod;
import com.rest.repository.PaymentRepository;
import java.util.List;
import java.util.Objects;

public class PaymentService {

    private final PaymentRepository repository;
    private int nextPaymentId = 1;

    public PaymentService() {
        this(new PaymentRepository());
    }

    public PaymentService(PaymentRepository repository) {
        this.repository = Objects.requireNonNull(repository, "repository cannot be null");
    }

    public Payment processPayment(PaymentMethod method, double amount) {
        Objects.requireNonNull(method, "payment method cannot be null");
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be a positive finite number.");
        }

        method.pay(amount);

        Payment payment = new Payment(nextPaymentId++,
                amount,
                method.getMethodName(),
                "Success");

        repository.save(payment);
        return payment;
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public Payment getPaymentById(int id) {
        return repository.findById(id);
    }
}
