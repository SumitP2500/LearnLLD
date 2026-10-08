package learn.pakinglot.models;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Payment extends BaseModel{
    PaymentMode paymentMode;
    PaymentStatus paymentStatus;
    Long TransactionId;
}
