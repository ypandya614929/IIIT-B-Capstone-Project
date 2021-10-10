package bookmyconsultation.appointmentservice.service;

import bookmyconsultation.appointmentservice.dto.AvailabilityServiceRequestDTO;
import bookmyconsultation.appointmentservice.dto.AvailabilityServiceResponseDTO;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface AvailabilityService {

    public void saveAvailability(String doctorId, AvailabilityServiceRequestDTO availabilityServiceRequestDTO);

    public AvailabilityServiceResponseDTO getAvailabilityByDoctor(String doctorId);
}
