package bookmyconsultation.paymentservice.service;

import bookmyconsultation.paymentservice.dto.PaymentServiceResponseDTO;

public interface PaymentService {

    public PaymentServiceResponseDTO makePayment(String appointmentId);

}
