package bookmyconsultation.appointmentservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Table(name = "Availability")
@Entity
public class AvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;

    @Column(name = "availability_date")
    private Date availabilityDate;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name="is_booked")
    @NotNull
    private boolean isBooked;

    @Column(name = "time_slot")
    private String timeSlot;

    public AvailabilityEntity() {
    }

    public AvailabilityEntity(@NotNull int id, Date availabilityDate, String doctorId, boolean isBooked, String timeSlot) {
        this.id = id;
        this.availabilityDate = availabilityDate;
        this.doctorId = doctorId;
        this.isBooked = isBooked;
        this.timeSlot = timeSlot;
    }

    public int getId() {
        return id;
    }

    public void setId(@NotNull int id) {
        this.id = id;
    }

    public Date getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(Date availabilityDate) {
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
