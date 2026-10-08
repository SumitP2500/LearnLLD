package learn.pakinglot.models;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Bill extends BaseModel{
    Long invoiceNumber;
    Ticket ticket;
    Date entryTime;
    Date exitTime;
    Gate gate;
    Operator operator;
    Vehicle vehicle;
    Double amount;
    List<Payment> payments;
    BillStatus billStatus;
    public static Long invoiceCount = 0L;

    public Bill() {
        setInvoiceNumber(++invoiceNumber);
    }
}
