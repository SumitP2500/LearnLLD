package learn.pakinglot.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * ParkingFloor
 */
@Getter 
@Setter 
public class ParkingFloor extends BaseModel {
    String number;
    List<ParkingSlot> parkingSlots;
    FloorStatus floorStatus;
    List<AllowedVehicle> allowedVehicles;
}
