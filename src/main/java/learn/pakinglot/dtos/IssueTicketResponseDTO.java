package learn.pakinglot.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class IssueTicketResponseDTO {
    private Long ticketNumber;  
    private Date entryTime;
    private ResponseDTO response;
}
