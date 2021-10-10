package bookmyconsultation.doctorservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonPropertyOrder({"id"})
public class DoctorServiceDTO {

    private String id;

    @Size(max = 255, message = "Invalid firstName")
    @NotBlank(message = "Invalid firstName")
    private String firstName;

    @Size(max = 255, message = "Invalid lastName")
    @NotBlank(message = "Invalid lastName")
    private String lastName;

    private String speciality="GENERAL_PHYSICIAN";

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
    @Pattern(regexp="(^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$)", message = "Please enter a valid birth date")
    private String dob;

    @Pattern(regexp="(^[0-9]{10})")
    @NotBlank
    private String mobile;

    @NotBlank
    private String emailId;

    @NotBlank
    @Pattern(regexp="(^[a-zA-Z0-9]{10})")
    private String pan;

    private String status="Pending";

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private String registrationDate;

    public DoctorServiceDTO() {
    }

    public DoctorServiceDTO(String id, @Size(max = 255, message = "Invalid firstName") @NotBlank(message = "Invalid firstName") String firstName, @Size(max = 255, message = "Invalid lastName") @NotBlank(message = "Invalid lastName") String lastName, String speciality, @Pattern(regexp = "(^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$)", message = "Please enter a valid birth date") String dob, @Pattern(regexp = "(^[0-9]{10})") @NotBlank String mobile, @NotBlank String emailId, @NotBlank @Pattern(regexp = "(^[a-zA-Z0-9]{10})") String pan, String status, String registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.dob = dob;
        this.mobile = mobile;
        this.emailId = emailId;
        this.pan = pan;
        this.status = status;
        this.registrationDate = registrationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "DoctorServiceDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", speciality='" + speciality + '\'' +
                ", dob='" + dob + '\'' +
                ", mobile='" + mobile + '\'' +
                ", emailId='" + emailId + '\'' +
                ", pan='" + pan + '\'' +
                ", status='" + status + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                '}';
    }
}
