package learn.pakinglot.models;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Vehicle extends BaseModel {
    Long registrationNumber;
    VehicleType vehicleType;
    String ownerName;
    String ownerPhone;
}
