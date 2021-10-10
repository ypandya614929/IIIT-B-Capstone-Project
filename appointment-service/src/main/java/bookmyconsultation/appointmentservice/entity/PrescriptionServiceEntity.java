package bookmyconsultation.appointmentservice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;


@Document(collection = "prescription")
public class PrescriptionServiceEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String userId;

    private String doctorId;

    private String doctorName;

    private String appointmentId;

    private String diagnosis;

    private List<HashMap<String, String>> medicineList;

    public PrescriptionServiceEntity() {
    }

    public PrescriptionServiceEntity(String id, String userId, String doctorId, String doctorName, String appointmentId, String diagnosis, List<HashMap<String, String>> medicineList) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.medicineList = medicineList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<HashMap<String, String>> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<HashMap<String, String>> medicineList) {
        this.medicineList = medicineList;
    }

    @Override
    public String toString() {
        return "PrescriptionServiceEntity{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", medicineList=" + medicineList +
                '}';
    }
}
