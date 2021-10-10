package bookmyconsultation.paymentservice.service;

import bookmyconsultation.paymentservice.dto.PaymentServiceResponseDTO;
import bookmyconsultation.paymentservice.entity.PaymentServiceEntity;
import bookmyconsultation.paymentservice.mapper.PaymentServiceMapper;
import bookmyconsultation.paymentservice.repository.PaymentServiceRepository;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements  PaymentService{

    @Autowired
    PaymentServiceRepository paymentServiceRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public PaymentServiceResponseDTO makePayment(String appointmentId) {
        if (paymentServiceRepository.existsById(appointmentId)){
            PaymentServiceEntity paymentServiceEntity = paymentServiceRepository.findById(appointmentId).get();
            paymentServiceEntity.setStatus("Confirmed");
            paymentServiceRepository.save(paymentServiceEntity);
            PaymentServiceResponseDTO paymentServiceResponseDTO = PaymentServiceMapper.EntityToDTO(paymentServiceEntity);
            String message = paymentServiceResponseDTO.toString();
//        producer.send(new ProducerRecord<>("message","message", message));
            return paymentServiceResponseDTO;
        }
        return null;
    }
}
