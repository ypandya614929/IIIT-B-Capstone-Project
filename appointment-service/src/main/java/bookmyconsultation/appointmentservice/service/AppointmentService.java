package bookmyconsultation.appointmentservice.service;

import bookmyconsultation.appointmentservice.dto.AppointmentServiceRequestDTO;
import bookmyconsultation.appointmentservice.dto.AppointmentServiceResponseDTO;
import bookmyconsultation.appointmentservice.dto.PrescriptionserviceRequestDTO;

import java.util.List;

public interface AppointmentService {

    public AppointmentServiceResponseDTO bookAppointment(AppointmentServiceRequestDTO appointmentServiceRequestDTO);

    public AppointmentServiceResponseDTO retrieveAppointment(String appointmentId);

    public List<AppointmentServiceResponseDTO> retrieveAppointmentByUserId(String userId);

    public void savePrescription(PrescriptionserviceRequestDTO prescriptionserviceRequestDTO);
}
