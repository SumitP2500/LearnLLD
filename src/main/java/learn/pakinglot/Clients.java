package learn.pakinglot;

import learn.pakinglot.controllers.BillController;
import learn.pakinglot.controllers.TicketController;
import learn.pakinglot.dtos.BillGenerateRequestDTO;
import learn.pakinglot.dtos.BillGenerationResponseDTO;
import learn.pakinglot.dtos.IssueTicketRequestDTO;
import learn.pakinglot.dtos.IssueTicketResponseDTO;
import learn.pakinglot.models.Gate;
import learn.pakinglot.models.GateType;
import learn.pakinglot.models.Operator;
import learn.pakinglot.models.ParkingFloor;
import learn.pakinglot.models.ParkingLot;
import learn.pakinglot.models.ParkingSlot;
import learn.pakinglot.models.Payment;
import learn.pakinglot.models.PaymentMode;
import learn.pakinglot.models.PaymentStatus;
import learn.pakinglot.models.Ticket;
import learn.pakinglot.models.Vehicle;
import learn.pakinglot.models.VehicleType;
import learn.pakinglot.repositories.InMemoryRepository;
import learn.pakinglot.services.BillServiceImpl;
import learn.pakinglot.services.TicketServiceImpl;

public class Clients {
    public static void main(String[] args) {

        InMemoryRepository<ParkingLot> parkingLotRepository = new InMemoryRepository<>();
        InMemoryRepository<ParkingFloor> parkingFloorRepository = new InMemoryRepository<>();
        InMemoryRepository<Gate> gateRepository = new InMemoryRepository<>();
        InMemoryRepository<ParkingSlot> parkingSlotRepository = new InMemoryRepository<>();
        InMemoryRepository<Ticket> ticketRepository = new InMemoryRepository<>();
        InMemoryRepository<Operator> operatorRepository = new InMemoryRepository<>();
        InMemoryRepository<Vehicle> vehicleRepository = new InMemoryRepository<>();
        InMemoryRepository<Payment> paymenyRepository = new InMemoryRepository<>();

        DataLoader loader = new DataLoader(parkingLotRepository, parkingFloorRepository, gateRepository,
                parkingSlotRepository, operatorRepository);
        loader.loadData();

        IssueTicketRequestDTO request = new IssueTicketRequestDTO();
        request.setOperatorId(operatorRepository.findAll().stream()
                .filter(op -> op.getGate().getGateType() == GateType.ENTRY).findFirst().get().getId());
        request.setVehicleNumber(12345L);
        request.setOwnerName("Sumit");
        request.setOwnerContact("9867892702");
        request.setVehicleType(VehicleType.TWO_WHEELER);
        // pass all InMemory repositories to TicketServiceImpl constructor
        TicketController ticketController = new TicketController(
                new TicketServiceImpl(ticketRepository, operatorRepository, parkingSlotRepository, vehicleRepository));

        IssueTicketResponseDTO ticketResponse = ticketController.issueTicket(request);
        System.out.println("Ticket Number: " + ticketResponse.getTicketNumber());
        System.out.println("Response: " + ticketResponse.getResponse().getMessage());

        //        IssueTicketRequestDTO request1 = new IssueTicketRequestDTO();
        // request1.setOperatorId(operatorRepository.findAll().stream()
        //         .filter(op -> op.getGate().getGateType() == GateType.ENTRY).findFirst().get().getId());
        // request1.setVehicleNumber(12345L);
        // request1.setOwnerName("Sumit");
        // request1.setOwnerContact("9867892702");
        // request1.setVehicleType(VehicleType.TWO_WHEELER);
        // // // pass all InMemory repositories to TicketServiceImpl constructor
        // // TicketController ticketController = new TicketController(
        // //         new TicketServiceImpl(ticketRepository, operatorRepository, parkingSlotRepository, vehicleRepository));

        // IssueTicketResponseDTO ticketResponse1 = ticketController.issueTicket(request);
        // System.out.println("Ticket Number: " + ticketResponse1.getTicketNumber());
        // System.out.println("Response: " + ticketResponse1.getResponse().getMessage());


        // // IssueTicketRequestDTO request2 = new IssueTicketRequestDTO();
        // // request2.setOperatorId(operatorRepository.findAll().stream()
        // //         .filter(op -> op.getGate().getGateType() == GateType.ENTRY).findFirst().get().getId());
        // // request2.setVehicleNumber(12345L);
        // // request2.setOwnerName("Sumit");
        // // request2.setOwnerContact("9867892702");
        // // request2.setVehicleType(VehicleType.TWO_WHEELER);

        // // // pass all InMemory repositories to TicketServiceImpl constructor
        // // // TicketController ticketController = new TicketController(
        // // //         new TicketServiceImpl(ticketRepository, operatorRepository, parkingSlotRepository, vehicleRepository));

        // // IssueTicketResponseDTO ticketResponse2 = ticketController.issueTicket(request);
        // // System.out.println("Ticket Number: " + ticketResponse2.getTicketNumber());
        // // System.out.println("Response: " + ticketResponse2.getResponse().getMessage());

        BillGenerateRequestDTO billRequest = new BillGenerateRequestDTO();
        billRequest.setOperatorId(operatorRepository.findAll().stream()
                .filter(op -> op.getGate().getGateType() == GateType.EXIT).findFirst().get().getId());
        billRequest.setPaymentMode(PaymentMode.CARD);
        billRequest.setPaymentStatus(PaymentStatus.SUCCESS);
        billRequest.setTicketNumber(ticketResponse.getTicketNumber());
        billRequest.setTransactionId(12345L);
        BillController billController = new BillController(new BillServiceImpl(operatorRepository, ticketRepository, paymenyRepository));
        BillGenerationResponseDTO billResponse = billController.generateBill(billRequest);
        System.out.println("Bill Response Message" + billResponse.getResponse().getMessage());
        System.out.println("Bill Invoice Numer :" + billResponse.getInvoiceNumber());
    }
}
