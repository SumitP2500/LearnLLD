package learn.pakinglot.strategy;

import java.util.List;

import learn.pakinglot.models.ParkingSlot;
import learn.pakinglot.models.SlotStatus;
import learn.pakinglot.models.VehicleType;

public class RandomSlotAssignmentStrategy implements SlotAssignmentStrategy {

    @Override
    public ParkingSlot getAvailableSlot(List<ParkingSlot> availableSlots, VehicleType vehicleType) {

        return availableSlots.stream().filter(slot -> slot.getSlotStatus() == SlotStatus.UNOCCUPIED).findFirst().get();
    }

}
