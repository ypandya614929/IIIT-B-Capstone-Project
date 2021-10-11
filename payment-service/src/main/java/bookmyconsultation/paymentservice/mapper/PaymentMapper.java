package bookmyconsultation.paymentservice.mapper;

import bookmyconsultation.paymentservice.dto.PaymentResponseDTO;
import bookmyconsultation.paymentservice.entity.PaymentEntity;
import java.time.OffsetDateTime;


public class PaymentMapper {

    public static PaymentResponseDTO EntityToDTO(PaymentEntity paymentServiceEntity){
        PaymentResponseDTO paymentServiceResponseDTO = new PaymentResponseDTO();
        paymentServiceResponseDTO.setId(paymentServiceEntity.getId());
        paymentServiceResponseDTO.setAppointmentId(paymentServiceEntity.getAppointmentId());
        paymentServiceResponseDTO.setCreateDate(OffsetDateTime.now().toString());
        return paymentServiceResponseDTO;
    }

    public static PaymentEntity DTOToEntity(String appointmentId){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAppointmentId(appointmentId);
        paymentEntity.setCreateDate(OffsetDateTime.now().toString());
        return paymentEntity;
    }

}
