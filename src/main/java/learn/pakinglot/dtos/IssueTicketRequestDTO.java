package learn.pakinglot.dtos;

import learn.pakinglot.models.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class IssueTicketRequestDTO {
    // private Long ParkingLotId;
    private Long OperatorId;
    private Long VehicleNumber;
    private String ownerName;
    private String ownerContact;
    private VehicleType vehicleType;
}
