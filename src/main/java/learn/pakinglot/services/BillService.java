package learn.pakinglot.services;

import learn.pakinglot.models.Bill;
import learn.pakinglot.models.PaymentMode;

/**
 * BillService
 */
public interface BillService {

    public Bill generateBill(Long operationId, Long registrationNumber, Long ticketNumber, Long paymentId, PaymentMode paymentMode, Long transactionId);
}