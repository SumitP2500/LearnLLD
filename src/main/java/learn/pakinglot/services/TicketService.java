package learn.pakinglot.services;

import learn.pakinglot.models.Ticket;
import learn.pakinglot.models.VehicleType;

public interface TicketService {

    Ticket issueTicket(Long OperatorId, VehicleType vehicleType, Long vehicleNumber, String ownerName, String ownerContact);

}
