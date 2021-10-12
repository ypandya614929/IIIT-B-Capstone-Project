package bookmyconsultation.appointmentservice.service;

import bookmyconsultation.appointmentservice.dto.AppointmentRequestDTO;
import bookmyconsultation.appointmentservice.dto.AppointmentResponseDTO;
import bookmyconsultation.appointmentservice.dto.PrescriptionRequestDTO;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface AppointmentService {

    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO appointmentServiceRequestDTO, String token) throws TemplateException, IOException, MessagingException;

    public AppointmentResponseDTO retrieveAppointment(String appointmentId);

    public List<AppointmentResponseDTO> retrieveAppointmentByUserId(String userId);

    public void savePrescription(PrescriptionRequestDTO prescriptionserviceRequestDTO, String token);
}
