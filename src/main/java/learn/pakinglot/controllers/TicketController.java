package learn.pakinglot.controllers;

import learn.pakinglot.dtos.IssueTicketRequestDTO;
import learn.pakinglot.dtos.IssueTicketResponseDTO;
import learn.pakinglot.dtos.ResponseDTO;
import learn.pakinglot.dtos.ResponseType;
import learn.pakinglot.models.Ticket;
import learn.pakinglot.services.TicketService;

public class TicketController {

    TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    
    
    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO request) {
        IssueTicketResponseDTO response = new IssueTicketResponseDTO();
        try {
            Ticket ticket = ticketService.issueTicket(request.getOperatorId(), request.getVehicleType(), request.getVehicleNumber(), request.getOwnerName(), request.getOwnerContact());
            response.setTicketNumber(ticket.getTicketNumber());
            response.setEntryTime(ticket.getEntryTime());
            response.setResponse(new ResponseDTO("Ticket has been created successfully", ResponseType.SUCCESS));
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            response.setResponse(new ResponseDTO("Unable to create ticket, check log for more details", ResponseType.ERROR));
        }
        return response;
    }
}
