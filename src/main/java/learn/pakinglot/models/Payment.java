package learn.pakinglot.models;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Payment {
    PaymentMode paymentMode;
    PaymentStatus paymentStatus;
    int TransactionId;
    
}
