package bookmyconsultation.appointmentservice.dto;

public class AppointmentServiceRequestDTO {

    private String doctorId;

    private String userId;

    private String timeSlot;

    private String appointmentDate;

    public AppointmentServiceRequestDTO() {
    }

    public AppointmentServiceRequestDTO(String doctorId, String userId, String timeSlot, String appointmentDate) {
        this.doctorId = doctorId;
        this.userId = userId;
        this.timeSlot = timeSlot;
        this.appointmentDate = appointmentDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "AppointmentServiceRequestDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", userId='" + userId + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                '}';
    }
}
