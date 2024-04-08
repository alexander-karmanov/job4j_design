package ru.job4j.ood.ocp.example3;

public class PaymentProcessor {
    public void processPayment(PaymentMode paymentMode) {
        if (paymentMode == PaymentMode.CASH) {
            CashPayment cashPayment = new CashPayment();
            cashPayment.acceptPayment();
        } else if (paymentMode == PaymentMode.CREDIT_CARD) {
            CreditCardPayment cardPayment = new CreditCardPayment();
            cardPayment.acceptPayment();
        }
    }
}
