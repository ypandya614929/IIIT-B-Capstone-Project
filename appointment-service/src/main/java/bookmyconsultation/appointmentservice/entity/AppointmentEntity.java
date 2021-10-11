package bookmyconsultation.appointmentservice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Document(collection = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    @Column(name = "appointment_id")
    private String id;

    @Column(name = "appointment_date")
    private String appointmentDate;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name="prior_medical_history")
    private String priorMedicalHistory;

    @Column(name = "status")
    private String status;

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "time_slot")
    private String timeSlot;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_email_id")
    private String userEmailId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "doctor_name")
    private String doctorName;

    public AppointmentEntity() {
    }

    public AppointmentEntity(String id, String appointmentDate, String createdDate, String doctorId, String priorMedicalHistory, String status, String symptoms, String timeSlot, String userId, String userEmailId, String userName, String doctorName) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.createdDate = createdDate;
        this.doctorId = doctorId;
        this.priorMedicalHistory = priorMedicalHistory;
        this.status = status;
        this.symptoms = symptoms;
        this.timeSlot = timeSlot;
        this.userId = userId;
        this.userEmailId = userEmailId;
        this.userName = userName;
        this.doctorName = doctorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPriorMedicalHistory() {
        return priorMedicalHistory;
    }

    public void setPriorMedicalHistory(String priorMedicalHistory) {
        this.priorMedicalHistory = priorMedicalHistory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "AppointmentServiceEntity{" +
                "id='" + id + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", priorMedicalHistory='" + priorMedicalHistory + '\'' +
                ", status='" + status + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", userId='" + userId + '\'' +
                ", userEmailId='" + userEmailId + '\'' +
                ", userName='" + userName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                '}';
    }
}
