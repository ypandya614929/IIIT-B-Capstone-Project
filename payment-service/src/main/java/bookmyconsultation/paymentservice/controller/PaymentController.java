package bookmyconsultation.paymentservice.controller;

import bookmyconsultation.paymentservice.dto.PaymentResponseDTO;
import bookmyconsultation.paymentservice.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping
@RestController
public class PaymentController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity makePayment(@RequestParam("appointmentId") String appointmentId){
        PaymentResponseDTO paymentServiceResponseDTO = paymentService.makePayment(appointmentId);
        return new ResponseEntity(paymentServiceResponseDTO, HttpStatus.OK);
    }

}
