package bookmyconsultation.paymentservice.service;

import bookmyconsultation.paymentservice.dto.PaymentResponseDTO;
import bookmyconsultation.paymentservice.entity.PaymentEntity;
import bookmyconsultation.paymentservice.mapper.PaymentMapper;
import bookmyconsultation.paymentservice.repository.PaymentRepository;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements  PaymentService{

    @Autowired
    PaymentRepository paymentServiceRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public PaymentResponseDTO makePayment(String appointmentId) {
        if (paymentServiceRepository.existsById(appointmentId)){
            PaymentEntity paymentServiceEntity = paymentServiceRepository.findById(appointmentId).get();
            paymentServiceEntity.setStatus("Confirmed");
            paymentServiceRepository.save(paymentServiceEntity);
            PaymentResponseDTO paymentServiceResponseDTO = PaymentMapper.EntityToDTO(paymentServiceEntity);
            String message = paymentServiceResponseDTO.toString();
//        producer.send(new ProducerRecord<>("message","message", message));
            return paymentServiceResponseDTO;
        }
        return null;
    }
}
