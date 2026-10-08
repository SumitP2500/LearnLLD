package learn.pakinglot.services;

import java.util.Date;
import java.util.List;

import learn.pakinglot.factory.FeesCalculationStrategyFactory;
import learn.pakinglot.models.Bill;
import learn.pakinglot.models.BillStatus;
import learn.pakinglot.models.Gate;
import learn.pakinglot.models.GateType;
import learn.pakinglot.models.Operator;
import learn.pakinglot.models.ParkingLot;
import learn.pakinglot.models.Payment;
import learn.pakinglot.models.PaymentMode;
import learn.pakinglot.models.SlotStatus;
import learn.pakinglot.models.Ticket;
import learn.pakinglot.models.Vehicle;
import learn.pakinglot.repositories.InMemoryRepository;
import learn.pakinglot.strategy.FeesCalculationStrategy;

public class BillServiceImpl implements BillService{

    InMemoryRepository<Operator> operatorRepository;
    InMemoryRepository<Ticket> ticketRepository;
    TicketService ticketService;
    VehicleService vehicleService;
    PaymentService paymentService;

    public BillServiceImpl(InMemoryRepository<Operator> operatorRepository, InMemoryRepository<Ticket> ticketRepository,
        InMemoryRepository<Payment> paymenyRepository ) {
            this.operatorRepository = operatorRepository;
            this.ticketRepository = ticketRepository;
            this.paymentService = new PaymentService(paymenyRepository);
    }

    @Override
    public Bill generateBill(Long operationId, Long registrationNumber, Long ticketNumber, Long paymentId, PaymentMode paymentMode,
            Long transactionId) {
        // get the operation from repository
        Operator operator = operatorRepository.findById(operationId);

        // get the gate from operator
        Gate gate = operator.getGate();
        
        // check gate is valid
        if(gate == null || gate.getGateType() != GateType.EXIT) {
            throw new IllegalArgumentException("Invalid gate");
        }

        // get the ticket from ticketNumber
        Ticket ticket = getTicketByTicketNumer(ticketNumber);
        
        // check that ticket is valid
        if(ticket== null) {
            throw new IllegalArgumentException("Invalid Ticekt");
        }

        // get the vehicle from repository and check its valid
        Vehicle vechile = vehicleService.getVehicleByRegistrationNumber(registrationNumber);
        if(vechile== null) {
            throw new IllegalArgumentException("Invalid vechile");
        }

        // get the parkingLot from operator
        ParkingLot parkingLot = gate.getParkingLot();

        // check parking lot is valid
        if(parkingLot== null) {
            throw new IllegalArgumentException("Invalid parkingLot");
        }

        // get the fees calculation strategy type
        FeesCalculationStrategy feesCalculationStrategy = FeesCalculationStrategyFactory.getFeesCalculationStrategy(parkingLot.getFeesCalculationStrategyType());
        
        // get the total fees that is going to charge by stragey factory logic
        Date exitTime = new Date();
        double fees = feesCalculationStrategy.calculateFees(ticket.getEntryTime(), exitTime, vechile.getVehicleType());
        
        // free the slot
        ticket.getParkingSlot().setSlotStatus(SlotStatus.UNOCCUPIED);
        parkingLot.getAllowedVehicles().forEach(allowedVehicle -> {
            if(allowedVehicle.getVehicleType() == vechile.getVehicleType()) {
                allowedVehicle.setCapacity(allowedVehicle.getCapacity()+1);
            }
        });

        // create or validate Payment
        Payment payment = paymentService.getOrCreatePayment(paymentMode, transactionId);
        
        // generate and return Bill
        Bill bill = new Bill();
        bill.setTicket(ticket);
        bill.setEntryTime(ticket.getEntryTime());
        bill.setExitTime(exitTime);
        bill.setGate(gate);
        bill.setOperator(operator);
        bill.setVehicle(vechile);
        bill.setAmount(fees);
        bill.setPayments(List.of(payment));
        bill.setBillStatus(BillStatus.PAID);
        return bill;
    }

    private Ticket getTicketByTicketNumer(Long ticketNumber) {
        return ticketRepository.findAll().stream().filter(t -> t.getTicketNumber() == ticketNumber).findFirst().orElse(null);
    }

}
