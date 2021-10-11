package bookmyconsultation.appointmentservice.service;

import bookmyconsultation.appointmentservice.dto.AppointmentRequestDTO;
import bookmyconsultation.appointmentservice.dto.AppointmentResponseDTO;
import bookmyconsultation.appointmentservice.dto.PrescriptionRequestDTO;
import bookmyconsultation.appointmentservice.dto.UserDetailDTO;
import bookmyconsultation.appointmentservice.entity.AppointmentEntity;
import bookmyconsultation.appointmentservice.entity.PrescriptionEntity;
import bookmyconsultation.appointmentservice.exception.PendingPaymentException;
import bookmyconsultation.appointmentservice.exception.ResouceNotFound;
import bookmyconsultation.appointmentservice.mapper.AppointmentServiceMapper;
import bookmyconsultation.appointmentservice.repository.AWSRepository;
import bookmyconsultation.appointmentservice.repository.AppointmentRepository;
import bookmyconsultation.appointmentservice.repository.PrescriptionRepository;
import freemarker.template.TemplateException;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    AWSRepository awsRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO appointmentServiceRequestDTO) throws TemplateException, IOException, MessagingException {
        UserDetailDTO userDetailDTO = getUserDetail(appointmentServiceRequestDTO.getUserId());
        AppointmentEntity appointmentServiceEntity = AppointmentServiceMapper.DTOToEntity(appointmentServiceRequestDTO);
        appointmentServiceEntity.setUserEmailId(userDetailDTO.getEmailId());
        appointmentServiceEntity.setUserName(userDetailDTO.getFirstName()+" "+userDetailDTO.getLastName());
        appointmentServiceRepository.save(appointmentServiceEntity);
        availabilityServiceImpl.updateAvailability(appointmentServiceEntity.getDoctorId(), appointmentServiceEntity.getAppointmentDate(), appointmentServiceEntity.getTimeSlot());
        AppointmentResponseDTO savedAppointmentServiceResponseDTO = AppointmentServiceMapper.EntityToDTO(appointmentServiceEntity);
        String message = savedAppointmentServiceResponseDTO.toString();
//        producer.send(new ProducerRecord<>("message","APPOINTMENT_SERVICE", message));
        awsRepository.sendEmail(userDetailDTO);
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
    public void savePrescription(PrescriptionRequestDTO prescriptionserviceRequestDTO) {
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
        prescriptionServiceRepository.save(prescriptionServiceEntity);
    }

    public UserDetailDTO getUserDetail(String userId) {
        String USER_SERVICE_URI = "http://USER-SERVICE/users/" + userId;

        // calling payment service API using rest template
        UserDetailDTO userDetailDTO = restTemplate.getForObject(USER_SERVICE_URI, UserDetailDTO.class);
        return userDetailDTO;
    }

}