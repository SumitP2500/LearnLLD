package learn.pakinglot.models;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Gate extends BaseModel {
    String name;
    GateType gateType;
    ParkingLot parkingLot;
    GateStatus gateStatus;
}