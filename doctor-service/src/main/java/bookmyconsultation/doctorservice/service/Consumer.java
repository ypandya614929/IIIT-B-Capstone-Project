package bookmyconsultation.doctorservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    DoctorServiceImpl doctorServiceImpl;

    @KafkaListener(topics = "doctor")
    public void processMessage(String content) {
        String[] values = content.split(",", 2);
        String doctorId = values[0];
        float rating = Float.parseFloat(values[1]);
        doctorServiceImpl.updateDoctorRatings(doctorId, rating);
    }

}