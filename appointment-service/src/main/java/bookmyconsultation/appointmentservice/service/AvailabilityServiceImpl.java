package bookmyconsultation.appointmentservice.service;

import bookmyconsultation.appointmentservice.dto.AvailabilityServiceRequestDTO;
import bookmyconsultation.appointmentservice.dto.AvailabilityServiceResponseDTO;
import bookmyconsultation.appointmentservice.entity.AvailabilityServiceEntity;
import bookmyconsultation.appointmentservice.repository.AWSRepository;
import bookmyconsultation.appointmentservice.repository.AvailabilityServiceRepository;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Producer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    AvailabilityServiceRepository availabilityServiceRepository;

    @Autowired
    AWSRepository awsRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public void saveAvailability(String doctorId, AvailabilityServiceRequestDTO availabilityServiceRequestDTO) {
        HashMap<String, List<String>> availabilityMap = availabilityServiceRequestDTO.getAvailabilityMap();
        availabilityMap.forEach((date, timeslotList) ->{
            for (String timeslot : timeslotList) {
                AvailabilityServiceEntity availability = new AvailabilityServiceEntity();
                availability.setBooked(false);
                availability.setDoctorId(doctorId);
                availability.setAvailabilityDate(date);
                availability.setTimeSlot(timeslot);
                availabilityServiceRepository.save(availability);
            }
        });
    }

    @Override
    public AvailabilityServiceResponseDTO getAvailabilityByDoctor(String doctorId) {
        if (doctorId == null){
            return null;
        }
        HashMap<String, List<String>> availabilityMap = new HashMap<>();
        List<AvailabilityServiceEntity> availabilityList = availabilityServiceRepository.findByDoctorIdAndIsBooked(doctorId, false);
        for (AvailabilityServiceEntity availabilityEntity : availabilityList){
            if (availabilityMap.containsKey(availabilityEntity.getAvailabilityDate())){
                List<String> timeslots = availabilityMap.get(availabilityEntity.getAvailabilityDate());
                timeslots.add(availabilityEntity.getTimeSlot());
            } else {
                List<String> timeslots = new ArrayList<>();
                timeslots.add(availabilityEntity.getTimeSlot());
                availabilityMap.put(availabilityEntity.getAvailabilityDate(), timeslots);
            }
        }
        AvailabilityServiceResponseDTO availabilityServiceResponseDTO = new AvailabilityServiceResponseDTO();
        availabilityServiceResponseDTO.setDoctorId(doctorId);
        availabilityServiceResponseDTO.setAvailabilityMap(availabilityMap);
        return availabilityServiceResponseDTO;
    }
}