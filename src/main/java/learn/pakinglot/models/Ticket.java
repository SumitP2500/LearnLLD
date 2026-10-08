package learn.pakinglot.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Ticket extends BaseModel {
    Long ticketNumber;
    Date entryTime;
    Gate gate;
    Vehicle vehicle;
    ParkingFloor parkingFloor;
    ParkingSlot parkingSlot;
    Operator operator;
    TicketStatus status;
    public static Long ticketCounter = 0L;

    public Ticket() {
        setTicketNumber(++ticketCounter);
    }

}
