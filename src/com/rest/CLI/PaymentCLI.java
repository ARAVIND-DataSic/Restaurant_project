package com.rest.CLI;

import com.rest.models.Payment;
import com.rest.payment.CardPayment;
import com.rest.payment.CashPayment;
import com.rest.payment.PaymentMethod;
import com.rest.payment.UPIPayment;
import com.rest.services.PaymentService;
import java.util.List;
import java.util.Scanner;

public class PaymentCLI {

    private final PaymentService service;
    private final Scanner scanner;

    public PaymentCLI() {
        this(new PaymentService(), new Scanner(System.in));
    }

    public PaymentCLI(Scanner scanner) {
        this(new PaymentService(), scanner);
    }

    public PaymentCLI(PaymentService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running && scanner.hasNextLine()) {
            System.out.println("\n == RESTRAURANT PAYMENT SYSTEM == ");
            System.out.println("1. Process Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. View Payment by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    processPayment();
                    break;
                case "2":
                    viewAllPayments();
                    break;
                case "3":
                    viewPaymentById();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting Payment System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }
    }

    private void processPayment() {
        System.out.print("Enter payment amount: ");
        if (!scanner.hasNextLine()) {
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
            if (!Double.isFinite(amount) || amount <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException exception) {
            System.out.println("Invalid amount. Please enter a valid number.");
            return;
        }

        System.out.println("Select payment method:");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.println("3. Cash");
        System.out.print("Enter your choice: ");

        if (!scanner.hasNextLine()) {
            return;
        }

        String choice = scanner.nextLine().trim();
        PaymentMethod method;
        switch (choice) {
            case "1":
                method = new UPIPayment();
                break;
            case "2":
                method = new CardPayment();
                break;
            case "3":
                method = new CashPayment();
                break;
            default:
            System.out.println("Invalid selection. Please choose a valid payment method.");
            return;
        }

        Payment payment = service.processPayment(method, amount);
        System.out.println("Payment processed successfully: " + payment);
    }

    private void viewAllPayments() {
        List<Payment> payments = service.getAllPayments();
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }

        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    private void viewPaymentById() {
        System.out.print("Enter payment ID: ");
        if (!scanner.hasNextLine()) {
            return;
        }

        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            System.out.println("Invalid payment ID.");
            return;
        }

        Payment payment = service.getPaymentById(id);
        if (payment == null) {
            System.out.println("Payment not found for ID: " + id);
            return;
        }

        System.out.println(payment);
    }
}
