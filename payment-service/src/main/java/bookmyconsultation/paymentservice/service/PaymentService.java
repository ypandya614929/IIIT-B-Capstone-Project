package bookmyconsultation.paymentservice.service;

import bookmyconsultation.paymentservice.dto.PaymentResponseDTO;

public interface PaymentService {

    public PaymentResponseDTO makePayment(String appointmentId);

}
