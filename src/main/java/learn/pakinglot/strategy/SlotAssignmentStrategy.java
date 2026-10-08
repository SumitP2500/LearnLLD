package learn.pakinglot.strategy;

import java.util.List;

import learn.pakinglot.models.ParkingSlot;
import learn.pakinglot.models.VehicleType;

public interface SlotAssignmentStrategy {
    
    ParkingSlot getAvailableSlot(List<ParkingSlot> availableSlots, VehicleType vehicleType);
}
