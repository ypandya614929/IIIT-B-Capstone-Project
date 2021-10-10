package bookmyconsultation.appointmentservice.dto;

import java.util.HashMap;
import java.util.List;

public class AvailabilityServiceResponseDTO {

    private String doctorId;
    private HashMap<String, List<String>> availabilityMap;

    public AvailabilityServiceResponseDTO() {
    }

    public AvailabilityServiceResponseDTO(String doctorId, HashMap<String, List<String>> availabilityMap) {
        this.doctorId = doctorId;
        this.availabilityMap = availabilityMap;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public HashMap<String, List<String>> getAvailabilityMap() {
        return availabilityMap;
    }

    public void setAvailabilityMap(HashMap<String, List<String>> availabilityMap) {
        this.availabilityMap = availabilityMap;
    }

    @Override
    public String toString() {
        return "AvailabilityServiceResponseDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", availabilityMap=" + availabilityMap +
                '}';
    }
}
