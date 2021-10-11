package bookmyconsultation.paymentservice.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PaymentResponseDTO {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String appointmentId;

    private String createDate;

    public PaymentResponseDTO() {
    }

    public PaymentResponseDTO(String id, String appointmentId, String createDate) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "PaymentServiceResponseDTO{" +
                "id='" + id + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
