package bookmyconsultation.appointmentservice.dto;

public class AppointmentRequestDTO {

    private String doctorId;

    private String doctorName;

    private String userId;

    private String timeSlot;

    private String appointmentDate;

    public AppointmentRequestDTO() {
    }

    public AppointmentRequestDTO(String doctorId, String doctorName, String userId, String timeSlot, String appointmentDate) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "AppointmentRequestDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", userId='" + userId + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                '}';
    }
}
