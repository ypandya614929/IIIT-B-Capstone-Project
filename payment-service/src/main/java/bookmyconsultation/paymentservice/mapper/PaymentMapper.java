package bookmyconsultation.paymentservice.mapper;

import bookmyconsultation.paymentservice.dto.PaymentResponseDTO;
import bookmyconsultation.paymentservice.entity.PaymentEntity;
import java.time.OffsetDateTime;


public class PaymentMapper {

    public static PaymentResponseDTO EntityToDTO(PaymentEntity paymentServiceEntity){
        PaymentResponseDTO paymentServiceResponseDTO = new PaymentResponseDTO();
        paymentServiceResponseDTO.setAppointmentId(paymentServiceEntity.getId());
        paymentServiceResponseDTO.setCreateDate(OffsetDateTime.now().toString());
        return paymentServiceResponseDTO;
    }

}
