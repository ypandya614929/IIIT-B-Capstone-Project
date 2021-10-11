package bookmyconsultation.appointmentservice.service;

import bookmyconsultation.appointmentservice.dto.AvailabilityRequestDTO;
import bookmyconsultation.appointmentservice.dto.AvailabilityResponseDTO;
import bookmyconsultation.appointmentservice.entity.AvailabilityEntity;
import bookmyconsultation.appointmentservice.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Producer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityServiceRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public void saveAvailability(String doctorId, AvailabilityRequestDTO availabilityServiceRequestDTO) {
        HashMap<String, List<String>> availabilityMap = availabilityServiceRequestDTO.getAvailabilityMap();
        availabilityMap.forEach((date, timeslotList) ->{
            for (String timeslot : timeslotList) {
                AvailabilityEntity availability = new AvailabilityEntity();
                availability.setBooked(false);
                availability.setDoctorId(doctorId);
                availability.setAvailabilityDate(Date.valueOf(date));
                availability.setTimeSlot(timeslot);
                deleteAvailability(availability.getDoctorId(), availability.getAvailabilityDate(), availability.getTimeSlot());
                availabilityServiceRepository.save(availability);
            }
        });
    }

    @Override
    public AvailabilityResponseDTO getAvailabilityByDoctor(String doctorId) {
        if (doctorId == null){
            return null;
        }
        HashMap<String, List<String>> availabilityMap = new HashMap<>();
        List<AvailabilityEntity> availabilityList = availabilityServiceRepository.findByDoctorIdAndIsBooked(doctorId, false);
        for (AvailabilityEntity availabilityEntity : availabilityList){
            if (availabilityMap.containsKey(availabilityEntity.getAvailabilityDate())){
                List<String> timeslots = availabilityMap.get(availabilityEntity.getAvailabilityDate());
                timeslots.add(availabilityEntity.getTimeSlot());
            } else {
                List<String> timeslots = new ArrayList<>();
                timeslots.add(availabilityEntity.getTimeSlot());
                availabilityMap.put(String.valueOf(availabilityEntity.getAvailabilityDate()), timeslots);
            }
        }
        AvailabilityResponseDTO availabilityServiceResponseDTO = new AvailabilityResponseDTO();
        availabilityServiceResponseDTO.setDoctorId(doctorId);
        availabilityServiceResponseDTO.setAvailabilityMap(availabilityMap);
        return availabilityServiceResponseDTO;
    }

    public void deleteAvailability(String doctorId, Date appointmentDate, String timeslot){
        List<AvailabilityEntity> availabilityList = availabilityServiceRepository.findByDoctorIdAndAvailabilityDateAndTimeSlot(doctorId, appointmentDate, timeslot);
        for(AvailabilityEntity availabilityServiceEntity: availabilityList){
            availabilityServiceRepository.delete(availabilityServiceEntity);
        }
    }

    public void updateAvailability(String doctorId, Date appointmentDate, String timeslot){
        List<AvailabilityEntity> availabilityList = availabilityServiceRepository.findByDoctorIdAndAvailabilityDateAndTimeSlot(doctorId, appointmentDate, timeslot);
        for(AvailabilityEntity availabilityServiceEntity: availabilityList){
            availabilityServiceEntity.setBooked(true);
            availabilityServiceRepository.save(availabilityServiceEntity);
        }
    }
}