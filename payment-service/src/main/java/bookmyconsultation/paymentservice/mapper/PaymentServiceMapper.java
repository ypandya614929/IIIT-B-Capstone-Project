package bookmyconsultation.paymentservice.mapper;

import bookmyconsultation.paymentservice.dto.PaymentServiceResponseDTO;
import bookmyconsultation.paymentservice.entity.PaymentServiceEntity;
import java.time.OffsetDateTime;


public class PaymentServiceMapper {

    public static PaymentServiceResponseDTO EntityToDTO(PaymentServiceEntity paymentServiceEntity){
        PaymentServiceResponseDTO paymentServiceResponseDTO = new PaymentServiceResponseDTO();
        paymentServiceResponseDTO.setAppointmentId(paymentServiceEntity.getId());
        paymentServiceResponseDTO.setCreateDate(OffsetDateTime.now().toString());
        return paymentServiceResponseDTO;
    }

}
