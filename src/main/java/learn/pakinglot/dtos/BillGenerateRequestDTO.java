package learn.pakinglot.dtos;

import learn.pakinglot.models.PaymentMode;
import learn.pakinglot.models.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class BillGenerateRequestDTO {
    Long ticketNumber;
    Long registrationNumber;
    Long operatorId;
    PaymentMode paymentMode;
    PaymentStatus paymentStatus;
    Long TransactionId;
    Long paymentId;
}
