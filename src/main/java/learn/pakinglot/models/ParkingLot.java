package learn.pakinglot.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class ParkingLot extends BaseModel{
    List<ParkingFloor> parkingFloors;
    List<Gate> gates;
    ParkingLotStatus parkingLotStatus;
    SlotAssignmentStrategyType slotAssignmentStrategyType;
    FeesCalculationStrategyType feesCalculationStrategyType;
    List<AllowedVehicle> allowedVehicles;
}
