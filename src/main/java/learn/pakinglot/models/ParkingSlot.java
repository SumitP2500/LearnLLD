package learn.pakinglot.models;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class ParkingSlot extends BaseModel {
    String number;
    SlotStatus slotStatus;
    VehicleType vehicleType;
}
