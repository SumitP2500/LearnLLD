package learn.pakinglot.controllers;

import learn.pakinglot.dtos.BillGenerateRequestDTO;
import learn.pakinglot.dtos.BillGenerationResponseDTO;
import learn.pakinglot.dtos.ResponseDTO;
import learn.pakinglot.dtos.ResponseType;
import learn.pakinglot.models.Bill;
import learn.pakinglot.services.BillService;

public class BillController {
    BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    public BillGenerationResponseDTO generateBill(BillGenerateRequestDTO request) {
        BillGenerationResponseDTO response = new BillGenerationResponseDTO();
        try {
            Bill bill = billService.generateBill(request.getOperatorId(), request.getRegistrationNumber(), request.getTicketNumber(), request.getPaymentId(), request.getPaymentMode(), request.getTransactionId());
            response.setInvoiceNumber(bill.getInvoiceNumber());
            response.setExitTime(bill.getExitTime());
            response.setResponse(new ResponseDTO("Bill has been generated successfully", ResponseType.SUCCESS));
        } catch(Exception e) {
            response.setResponse(new ResponseDTO("Issue has been detected while generating Bill", ResponseType.ERROR));
            e.printStackTrace();
        }
        return response;
    }
}
