package bookmyconsultation.appointmentservice.service;

import bookmyconsultation.appointmentservice.dto.*;
import bookmyconsultation.appointmentservice.entity.AppointmentEntity;
import bookmyconsultation.appointmentservice.entity.PrescriptionEntity;
import bookmyconsultation.appointmentservice.exception.PendingPaymentException;
import bookmyconsultation.appointmentservice.exception.ResouceNotFound;
import bookmyconsultation.appointmentservice.mapper.AppointmentServiceMapper;
import bookmyconsultation.appointmentservice.repository.AppointmentRepository;
import bookmyconsultation.appointmentservice.repository.PrescriptionRepository;
import freemarker.template.TemplateException;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentServiceRepository;

    @Autowired
    AvailabilityServiceImpl availabilityServiceImpl;

    @Autowired
    PrescriptionRepository prescriptionServiceRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Producer<String, String> producer;

    @Override
    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO appointmentServiceRequestDTO, String token) throws TemplateException, IOException, MessagingException {
        UserDetailDTO userDetailDTO = getUserDetail(appointmentServiceRequestDTO.getUserId(), token);
        AppointmentEntity appointmentServiceEntity = AppointmentServiceMapper.DTOToEntity(appointmentServiceRequestDTO);
        appointmentServiceEntity.setUserEmailId(userDetailDTO.getEmailId());
        appointmentServiceEntity.setUserName(userDetailDTO.getFirstName()+" "+userDetailDTO.getLastName());
        appointmentServiceRepository.save(appointmentServiceEntity);
        availabilityServiceImpl.updateAvailability(appointmentServiceEntity.getDoctorId(), appointmentServiceEntity.getAppointmentDate(), appointmentServiceEntity.getTimeSlot());
        AppointmentResponseDTO savedAppointmentServiceResponseDTO = AppointmentServiceMapper.EntityToDTO(appointmentServiceEntity);
        String message = appointmentServiceEntity.toString();
        producer.send(new ProducerRecord<>("APPOINTMENT_SERVICE","APPOINTMENT_SERVICE", message));
        return savedAppointmentServiceResponseDTO;
    }

    @Override
    public AppointmentResponseDTO retrieveAppointment(String appointmentId) {
        if (appointmentServiceRepository.existsById(appointmentId)) {
            AppointmentEntity appointmentServiceEntity = appointmentServiceRepository.findById(appointmentId).get();
            AppointmentResponseDTO savedAppointmentServiceResponseDTO = AppointmentServiceMapper.EntityToDTO(appointmentServiceEntity);
            return savedAppointmentServiceResponseDTO;
        }
        throw new ResouceNotFound();
    }

    @Override
    public List<AppointmentResponseDTO> retrieveAppointmentByUserId(String userId) {
        List<AppointmentResponseDTO> appointmentServiceResponseDTOList = new ArrayList<>();
        for(AppointmentEntity appointmentServiceEntity : appointmentServiceRepository.findByUserId(userId)){
            appointmentServiceResponseDTOList.add(AppointmentServiceMapper.EntityToDTO(appointmentServiceEntity));
        }
        return appointmentServiceResponseDTOList;
    }

    @Override
    public void savePrescription(PrescriptionRequestDTO prescriptionserviceRequestDTO, String token) {
        try {
            AppointmentResponseDTO appointmentServiceResponseDTO = retrieveAppointment(prescriptionserviceRequestDTO.getAppointmentId());
            if (appointmentServiceResponseDTO.getStatus().equalsIgnoreCase("PendingPayment")){
                throw new PendingPaymentException();
            }
        } catch (Exception e){
            throw new PendingPaymentException();
        }
        PrescriptionEntity prescriptionServiceEntity = new PrescriptionEntity();
        prescriptionServiceEntity.setAppointmentId(prescriptionserviceRequestDTO.getAppointmentId());
        prescriptionServiceEntity.setDoctorId(prescriptionserviceRequestDTO.getDoctorId());
        prescriptionServiceEntity.setDoctorName(prescriptionserviceRequestDTO.getDoctorName());
        prescriptionServiceEntity.setUserId(prescriptionserviceRequestDTO.getUserId());
        prescriptionServiceEntity.setDiagnosis(prescriptionserviceRequestDTO.getDiagnosis());
        prescriptionServiceEntity.setMedicineList(prescriptionserviceRequestDTO.getMedicineList());
        String message = prescriptionServiceEntity.toString();
        UserDetailDTO userDetailDTO = getUserDetail(prescriptionserviceRequestDTO.getUserId(), token);
        message += ", userEmailId=" + userDetailDTO.getEmailId();
        producer.send(new ProducerRecord<>("PRESCRIPTION","PRESCRIPTION", message));
        prescriptionServiceRepository.save(prescriptionServiceEntity);
    }

    public UserDetailDTO getUserDetail(String userId, String token) {
        String USER_SERVICE_URI = "http://USER-SERVICE/users/" + userId;

        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity request=new HttpEntity(headers);

        try {
            ResponseEntity responseEntity = restTemplate.exchange(USER_SERVICE_URI, HttpMethod.GET, request, UserDetailDTO.class);
            UserDetailDTO userDetailDTO = (UserDetailDTO) responseEntity.getBody();
            return userDetailDTO;
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public void updateAppointmentStatus(String message){
        message = message.replace("PaymentServiceResponseDTO", "");
        message = message.replace("{", "").replace("}", "").replace("'", "");
        Map<String,String> map = new LinkedHashMap<String,String>();
        for(String keyValue: message.split(", ")) {
            String[] parts = keyValue.split("=", 2);
            map.put(parts[0], parts[1]);
        }
        String appointmentId = map.get("appointmentId");
        if (appointmentServiceRepository.existsById(appointmentId)) {
            AppointmentEntity appointmentServiceEntity = appointmentServiceRepository.findById(appointmentId).get();
            appointmentServiceEntity.setStatus("Confirmed");
            appointmentServiceRepository.save(appointmentServiceEntity);
        }
    }

}