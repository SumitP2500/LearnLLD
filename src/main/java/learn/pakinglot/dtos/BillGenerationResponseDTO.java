package learn.pakinglot.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class BillGenerationResponseDTO {
    Long invoiceNumber;
    Date exitTime;
    ResponseDTO response;
}
