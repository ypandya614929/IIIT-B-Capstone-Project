package bookmyconsultation.appointmentservice.service;

import bookmyconsultation.appointmentservice.dto.AvailabilityRequestDTO;
import bookmyconsultation.appointmentservice.dto.AvailabilityResponseDTO;

public interface AvailabilityService {

    public void saveAvailability(String doctorId, AvailabilityRequestDTO availabilityServiceRequestDTO);

    public AvailabilityResponseDTO getAvailabilityByDoctor(String doctorId);
}
