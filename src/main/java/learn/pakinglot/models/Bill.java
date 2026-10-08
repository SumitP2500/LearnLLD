package learn.pakinglot.models;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Bill {
    int invoiceNumber;
    Ticket ticket;
    Date exitTime;
    Gate gate;
    Operator operator;
    Integer amount;
    List<Payment> payments;
    BillStatus billStatus;
}
