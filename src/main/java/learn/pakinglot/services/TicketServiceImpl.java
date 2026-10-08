package learn.pakinglot.services;

import java.util.Date;
import java.util.List;

import learn.pakinglot.factory.SlotAssignmentStrategyFactory;
import learn.pakinglot.models.AllowedVehicle;
import learn.pakinglot.models.Gate;
import learn.pakinglot.models.GateType;
import learn.pakinglot.models.Operator;
import learn.pakinglot.models.ParkingLot;
import learn.pakinglot.models.ParkingLotStatus;
import learn.pakinglot.models.ParkingSlot;
import learn.pakinglot.models.SlotStatus;
import learn.pakinglot.models.Ticket;
import learn.pakinglot.models.TicketStatus;
import learn.pakinglot.models.Vehicle;
import learn.pakinglot.models.VehicleType;
import learn.pakinglot.repositories.InMemoryRepository;
import learn.pakinglot.strategy.SlotAssignmentStrategy;

public class TicketServiceImpl implements TicketService {

    InMemoryRepository<Operator> operatorRepository;
    InMemoryRepository<ParkingSlot> parkingSlotRepository;
    InMemoryRepository<Ticket> ticketRepository;
    VehicleService vehicleService;
    
    public TicketServiceImpl(InMemoryRepository<Ticket> ticketRepository,
            InMemoryRepository<Operator> operatorRepository, InMemoryRepository<ParkingSlot> parkingSlotRepository,
             InMemoryRepository<Vehicle> vehicleRepository) {
        this.ticketRepository = ticketRepository;
        this.operatorRepository = operatorRepository;
        this.parkingSlotRepository = parkingSlotRepository;
        this.vehicleService = new VehicleService(vehicleRepository);
    }

    @Override
    public Ticket issueTicket(Long OperatorId, VehicleType vehicleType, Long vehicleNumber, String ownerName, String ownerContact) {
        // get the Operator from the operatorId
        Operator operator = operatorRepository.findById(OperatorId);

        // get the gate from the operator and
        Gate gate = operator.getGate();
        
        // valid the gate by checking its null and its entry gate only
        if(gate == null || gate.getGateType() != GateType.ENTRY) {
            throw new IllegalArgumentException("Invalid gate");
        }

        // get the parking lot from the gate
        ParkingLot parkingLot = gate.getParkingLot();
        // validate the parking lot by checking its null and its status is open
        if(parkingLot == null || parkingLot.getParkingLotStatus() != ParkingLotStatus.OPERATIONAL) {
            throw new IllegalArgumentException("Invalid parking lot");
        }

        // get or create vehicle from repository by vehicle number
        Vehicle vehicle = vehicleService.getOrCreateVehicle(vehicleNumber, vehicleType, ownerName, ownerContact);
        
        // check allowedVehicle has available slots
        List<AllowedVehicle> allowedVehicles = parkingLot.getAllowedVehicles();
        for(AllowedVehicle v : allowedVehicles) {
            if(v.getVehicleType()==vehicleType && v.getCapacity()<1) {
                throw new IllegalArgumentException("Capacity closed.");
            }
        }

        // get the all available parking slots
        List<ParkingSlot> availableSlots = parkingSlotRepository.findAll();

        // get the parking slot from strategy by passing the vehicle type and available parking slots
        SlotAssignmentStrategy slotAssignmentStrategy = SlotAssignmentStrategyFactory.getSlotAssignmentStrategy(parkingLot.getSlotAssignmentStrategyType());
        ParkingSlot parkingSlot = slotAssignmentStrategy.getAvailableSlot(availableSlots, vehicleType);
        // validate the parking slot by checking its null and its status is available
        if(parkingSlot == null || parkingSlot.getSlotStatus() != SlotStatus.UNOCCUPIED) {
            throw new IllegalArgumentException("No available parking slot");
        }

        parkingSlot.setSlotStatus(SlotStatus.OCCUPIED);

        for(AllowedVehicle v : allowedVehicles) {
            if(v.getVehicleType()==vehicleType) {
                v.setCapacity(v.getCapacity()-1);
                break;
            }
        }

        // create the ticket and save it to the repository and return the ticket
        Ticket ticket = new Ticket();
        ticket.setParkingSlot(parkingSlot);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        ticket.setGate(gate);
        ticket.setOperator(operator);
        ticket.setStatus(TicketStatus.OPEN);
        ticketRepository.save(ticket);

        return ticket;
    }
}
