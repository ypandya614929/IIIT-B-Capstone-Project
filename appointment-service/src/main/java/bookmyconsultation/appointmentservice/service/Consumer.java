package bookmyconsultation.appointmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    AppointmentServiceImpl appointmentServiceImpl;

    @KafkaListener(topics = "payment")
    public void processMessage(String content) {
        appointmentServiceImpl.updateAppointmentStatus(content);
    }

}