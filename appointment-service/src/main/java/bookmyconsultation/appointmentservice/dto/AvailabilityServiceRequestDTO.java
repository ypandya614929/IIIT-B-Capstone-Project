package bookmyconsultation.appointmentservice.dto;

import java.util.List;
import java.util.HashMap;

public class AvailabilityServiceRequestDTO {

    private HashMap<String, List<String>> availabilityMap;

    public AvailabilityServiceRequestDTO() {
    }

    public AvailabilityServiceRequestDTO(HashMap<String, List<String>> availabilityMap) {
        this.availabilityMap = availabilityMap;
    }

    public HashMap<String, List<String>> getAvailabilityMap() {
        return availabilityMap;
    }

    public void setAvailabilityMap(HashMap<String, List<String>> availabilityMap) {
        this.availabilityMap = availabilityMap;
    }

    @Override
    public String toString() {
        return "AvailabilityServiceRequestDTO{" +
                "availabilityMap=" + availabilityMap +
                '}';
    }
}
