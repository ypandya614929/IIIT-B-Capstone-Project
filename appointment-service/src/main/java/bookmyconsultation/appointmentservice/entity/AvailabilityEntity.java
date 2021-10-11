package bookmyconsultation.appointmentservice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Document(collection = "availability")
public class AvailabilityEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "availability_date")
    private String availabilityDate;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name="is_booked")
    private boolean isBooked;

    @Column(name = "time_slot")
    private String timeSlot;

    public AvailabilityEntity() {
    }

    public AvailabilityEntity(String id, String availabilityDate, String doctorId, boolean isBooked, String timeSlot) {
        this.id = id;
        this.availabilityDate = availabilityDate;
        this.doctorId = doctorId;
        this.isBooked = isBooked;
        this.timeSlot = timeSlot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(String availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "AvailabilityServiceEntity{" +
                "id='" + id + '\'' +
                ", availabilityDate=" + availabilityDate +
                ", doctorId='" + doctorId + '\'' +
                ", isBooked=" + isBooked +
                ", timeSlot='" + timeSlot + '\'' +
                '}';
    }
}
