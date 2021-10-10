package bookmyconsultation.paymentservice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Document(collection = "appointment")
public class PaymentServiceEntity {

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

    public PaymentServiceEntity() {
    }

    public PaymentServiceEntity(String id, String appointmentDate, String createdDate, String doctorId, String priorMedicalHistory, String status, String symptoms, String timeSlot, String userId, String userEmailId, String userName, String doctorName) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentServiceEntity{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
