package learn.pakinglot;

import java.util.List;

import learn.pakinglot.models.AllowedVehicle;
import learn.pakinglot.models.FeesCalculationStrategyType;
import learn.pakinglot.models.FloorStatus;
import learn.pakinglot.models.Gate;
import learn.pakinglot.models.GateStatus;
import learn.pakinglot.models.GateType;
import learn.pakinglot.models.Operator;
import learn.pakinglot.models.ParkingFloor;
import learn.pakinglot.models.ParkingLot;
import learn.pakinglot.models.ParkingLotStatus;
import learn.pakinglot.models.ParkingSlot;
import learn.pakinglot.models.SlotAssignmentStrategyType;
import learn.pakinglot.models.SlotStatus;
import learn.pakinglot.models.VehicleType;
import learn.pakinglot.repositories.InMemoryRepository;

/**
 * DataLoader
 */
public class DataLoader {
    InMemoryRepository<ParkingLot> parkingLotRepository;
    InMemoryRepository<ParkingFloor> parkingFloorRepository;
    InMemoryRepository<Gate> gateRepository;
    InMemoryRepository<ParkingSlot> parkingSlotRepository;
    InMemoryRepository<Operator> operatorRepository;

    public DataLoader(InMemoryRepository<ParkingLot> parkingLotRepository,
                      InMemoryRepository<ParkingFloor> parkingFloorRepository,
                      InMemoryRepository<Gate> gateRepository,
                      InMemoryRepository<ParkingSlot> parkingSlotRepository, 
                      InMemoryRepository<Operator> operatorRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingFloorRepository = parkingFloorRepository;
        this.gateRepository = gateRepository;
        this.parkingSlotRepository = parkingSlotRepository;
        this.operatorRepository = operatorRepository;
    }

    public void loadData() {
        ParkingLot parkingLot = new ParkingLot();
        
        parkingLot.setParkingLotStatus(ParkingLotStatus.OPERATIONAL);
        parkingLot.setSlotAssignmentStrategyType(SlotAssignmentStrategyType.RANDOM);
        parkingLot.setFeesCalculationStrategyType(FeesCalculationStrategyType.HOURLY);

        ParkingFloor firstFloor = new ParkingFloor();
        firstFloor.setNumber("1");
        firstFloor.setFloorStatus(FloorStatus.AVAILABLE);
        firstFloor.setAllowedVehicles(null);

        ParkingFloor secondFloor = new ParkingFloor();
        secondFloor.setNumber("2");
        secondFloor.setFloorStatus(FloorStatus.AVAILABLE);
        secondFloor.setAllowedVehicles(null);

        parkingLot.setParkingFloors(List.of(firstFloor, secondFloor));

        Gate entryGate = new Gate();
        entryGate.setName("Entry Gate");
        entryGate.setGateType(GateType.ENTRY);
        entryGate.setParkingLot(parkingLot);
        entryGate.setGateStatus(GateStatus.OPEN);
        gateRepository.save(entryGate);

        Gate exitGate = new Gate();
        exitGate.setName("Exit Gate");
        exitGate.setGateType(GateType.EXIT);
        exitGate.setParkingLot(parkingLot);
        exitGate.setGateStatus(GateStatus.OPEN);
        gateRepository.save(exitGate);

        parkingLot.setGates(List.of(entryGate, exitGate));

        ParkingSlot parkingSlot1 = new ParkingSlot();
        parkingSlot1.setNumber("1A");   
        parkingSlot1.setSlotStatus(SlotStatus.UNOCCUPIED);
        parkingSlot1.setVehicleType(VehicleType.TWO_WHEELER);
        parkingSlotRepository.save(parkingSlot1);

        ParkingSlot parkingSlot2 = new ParkingSlot();
        parkingSlot2.setNumber("1B");
        parkingSlot2.setSlotStatus(SlotStatus.UNOCCUPIED);
        parkingSlot2.setVehicleType(VehicleType.FOUR_WHEELER);
        parkingSlotRepository.save(parkingSlot2);

        ParkingSlot parkingSlot3 = new ParkingSlot();
        parkingSlot3.setNumber("2A");
        parkingSlot3.setSlotStatus(SlotStatus.UNOCCUPIED);
        parkingSlot3.setVehicleType(VehicleType.FOUR_WHEELER);
        parkingSlotRepository.save(parkingSlot3);

        ParkingSlot parkingSlot4 = new ParkingSlot();
        parkingSlot4.setNumber("2B");
        parkingSlot4.setSlotStatus(SlotStatus.UNOCCUPIED);
        parkingSlot4.setVehicleType(VehicleType.TWO_WHEELER);
        parkingSlotRepository.save(parkingSlot4);

        firstFloor.setParkingSlots(List.of(parkingSlot1, parkingSlot2));
        secondFloor.setParkingSlots(List.of(parkingSlot3, parkingSlot4));
        
        parkingFloorRepository.save(firstFloor);
        parkingFloorRepository.save(secondFloor);

        AllowedVehicle allowedVehicle1 = new AllowedVehicle();
        allowedVehicle1.setVehicleType(VehicleType.TWO_WHEELER);
        allowedVehicle1.setCapacity(2);

        AllowedVehicle allowedVehicle2 = new AllowedVehicle();
        allowedVehicle2.setVehicleType(VehicleType.FOUR_WHEELER);
        allowedVehicle2.setCapacity(2);

        parkingLot.setAllowedVehicles(List.of(allowedVehicle1, allowedVehicle2));
        parkingLotRepository.save(parkingLot);

        Operator entryOperator = new Operator();
        entryOperator.setName("Entry Operator");
        entryOperator.setGate(entryGate);
        entryOperator.setEmployeeId("EMP001");
        operatorRepository.save(entryOperator);

        Operator exitOperator = new Operator();
        exitOperator.setName("Exit Operator");
        exitOperator.setGate(exitGate);
        exitOperator.setEmployeeId("EMP002");   
        operatorRepository.save(exitOperator);
    }

}
