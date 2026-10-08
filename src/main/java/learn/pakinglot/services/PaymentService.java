package learn.pakinglot.services;

import learn.pakinglot.models.Payment;
import learn.pakinglot.models.PaymentMode;
import learn.pakinglot.models.PaymentStatus;
import learn.pakinglot.repositories.InMemoryRepository;

public class PaymentService {

    InMemoryRepository<Payment> paymenyRepository;

    public PaymentService(InMemoryRepository<Payment> paymenyRepository) {
        this.paymenyRepository = paymenyRepository;
    }

    public Payment getOrCreatePayment(PaymentMode paymentMode, Long transactionId) {
        Payment payment = getPaymentByTransactionId(transactionId);
        if(payment==null) {
            payment = new Payment();
            payment.setPaymentMode(paymentMode);
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setTransactionId(transactionId);
        }
        return payment;
    }

    public Payment getPaymentByTransactionId(Long transactionId) {
        return paymenyRepository.findAll().stream().filter(p -> p.getTransactionId()==transactionId).findFirst().orElse(null);
    }

}
