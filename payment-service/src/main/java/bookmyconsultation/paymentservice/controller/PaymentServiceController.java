package bookmyconsultation.paymentservice.controller;

import bookmyconsultation.paymentservice.dto.PaymentServiceResponseDTO;
import bookmyconsultation.paymentservice.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping
@RestController
public class PaymentServiceController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity makePayment(@RequestParam("appointmentId") String appointmentId){
        PaymentServiceResponseDTO paymentServiceResponseDTO = paymentService.makePayment(appointmentId);
        return new ResponseEntity(paymentServiceResponseDTO, HttpStatus.OK);
    }

}
