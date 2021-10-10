package bookmyconsultation.appointmentservice.service;

import bookmyconsultation.appointmentservice.dto.AppointmentServiceRequestDTO;
import bookmyconsultation.appointmentservice.dto.AppointmentServiceResponseDTO;
import bookmyconsultation.appointmentservice.dto.PrescriptionserviceRequestDTO;
import bookmyconsultation.appointmentservice.entity.AppointmentServiceEntity;
import bookmyconsultation.appointmentservice.entity.AvailabilityServiceEntity;
import bookmyconsultation.appointmentservice.entity.PrescriptionMedicineEntity;
import bookmyconsultation.appointmentservice.entity.PrescriptionServiceEntity;
import bookmyconsultation.appointmentservice.mapper.AppointmentServiceMapper;
import bookmyconsultation.appointmentservice.repository.AWSRepository;
import bookmyconsultation.appointmentservice.repository.AppointmentServiceRepository;
import bookmyconsultation.appointmentservice.repository.PrescriptionServiceRepository;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentServiceRepository appointmentServiceRepository;

    @Autowired
    PrescriptionServiceRepository prescriptionServiceRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AWSRepository awsRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public AppointmentServiceResponseDTO bookAppointment(AppointmentServiceRequestDTO appointmentServiceRequestDTO) {
        AppointmentServiceEntity appointmentServiceEntity = AppointmentServiceMapper.DTOToEntity(appointmentServiceRequestDTO);
        appointmentServiceRepository.save(appointmentServiceEntity);
        AppointmentServiceResponseDTO savedAppointmentServiceResponseDTO = AppointmentServiceMapper.EntityToDTO(appointmentServiceEntity);
        String message = savedAppointmentServiceResponseDTO.toString();
//        producer.send(new ProducerRecord<>("message","message", message));
//        awsRepository.sendEmail(savedDoctorDTO);
        return savedAppointmentServiceResponseDTO;
    }

    @Override
    public AppointmentServiceResponseDTO retrieveAppointment(String appointmentId) {
        if (appointmentServiceRepository.existsById(appointmentId)) {
            AppointmentServiceEntity appointmentServiceEntity = appointmentServiceRepository.findById(appointmentId).get();
            AppointmentServiceResponseDTO savedAppointmentServiceResponseDTO = AppointmentServiceMapper.EntityToDTO(appointmentServiceEntity);
            return savedAppointmentServiceResponseDTO;
        }
        return null;
    }

    @Override
    public List<AppointmentServiceResponseDTO> retrieveAppointmentByUserId(String userId) {
        List<AppointmentServiceResponseDTO> appointmentServiceResponseDTOList = new ArrayList<>();
        for(AppointmentServiceEntity appointmentServiceEntity : appointmentServiceRepository.findByUserId(userId)){
            appointmentServiceResponseDTOList.add(AppointmentServiceMapper.EntityToDTO(appointmentServiceEntity));
        }
        return appointmentServiceResponseDTOList;
    }

    @Override
    public void savePrescription(PrescriptionserviceRequestDTO prescriptionserviceRequestDTO) {
        PrescriptionServiceEntity prescriptionServiceEntity = new PrescriptionServiceEntity();
        prescriptionServiceEntity.setAppointmentId(prescriptionserviceRequestDTO.getAppointmentId());
        prescriptionServiceEntity.setDoctorId(prescriptionserviceRequestDTO.getDoctorId());
        prescriptionServiceEntity.setDoctorName(prescriptionserviceRequestDTO.getDoctorName());
        prescriptionServiceEntity.setUserId(prescriptionserviceRequestDTO.getUserId());
        prescriptionServiceEntity.setDiagnosis(prescriptionserviceRequestDTO.getDiagnosis());
        prescriptionServiceEntity.setMedicineList(prescriptionserviceRequestDTO.getMedicineList());
        prescriptionServiceRepository.save(prescriptionServiceEntity);

//
//        medicineList.forEach((date, timeslotList) ->{
//            for (String timeslot : timeslotList) {
//                availability.setBooked(false);
//                availability.setDoctorId(doctorId);
//                availability.setAvailabilityDate(date);
//                availability.setTimeSlot(timeslot);
//                availabilityServiceRepository.save(availability);
//            }
//        });
    }

//        Map<String,String> paymentUriMap = new HashMap<>();
//        paymentUriMap.put("paymentMode", bookingTransactionDTO.getPaymentMode());
//        paymentUriMap.put("bookingId", String.valueOf(bookingTransactionDTO.getBookingId()));
//        paymentUriMap.put("upiId", bookingTransactionDTO.getUpiId());
//        paymentUriMap.put("cardNumber", bookingTransactionDTO.getCardNumber());
//
//        // calling payment service API using rest template
//        BookingTransactionDTO updateBookingTransactionDTO = restTemplate.postForObject("http://PAYMENT-SERVICE/transaction", paymentUriMap, BookingTransactionDTO.class);
//
//        return null;
}