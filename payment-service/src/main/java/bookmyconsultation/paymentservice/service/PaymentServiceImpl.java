package bookmyconsultation.paymentservice.service;

import bookmyconsultation.paymentservice.dto.PaymentResponseDTO;
import bookmyconsultation.paymentservice.entity.PaymentEntity;
import bookmyconsultation.paymentservice.mapper.PaymentMapper;
import bookmyconsultation.paymentservice.repository.PaymentRepository;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
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
        PaymentEntity paymentServiceEntity = PaymentMapper.DTOToEntity(appointmentId);
        paymentServiceRepository.save(paymentServiceEntity);
        PaymentResponseDTO paymentServiceResponseDTO = PaymentMapper.EntityToDTO(paymentServiceEntity);
        String message = paymentServiceResponseDTO.toString();
        producer.send(new ProducerRecord<>("payment","PAYMENT_SERVICE", message));
        return paymentServiceResponseDTO;
    }
}
