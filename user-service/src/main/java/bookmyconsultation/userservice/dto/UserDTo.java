package bookmyconsultation.userservice.dto;

import bookmyconsultation.userservice.validator.EmailValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// this class is used to map user data to and from Entity
@JsonPropertyOrder({"id"})
public class UserDTo {

    private String id;

    @Size(max = 255, message = "First Name")
    @NotBlank(message = "First Name")
    private String firstName;

    @Size(max = 255, message = "Last Name")
    @NotBlank(message = "Last Name")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @Pattern(regexp="(^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$)", message = "Date Of Birth")
    private String dob;

    @Pattern(regexp="(^[0-9]{10})", message = "Mobile")
    @Size(max = 10, min = 10, message = "Mobile")
    @NotBlank(message = "Mobile")
    private String mobile;

    @NotBlank(message = "Email Id")
    @EmailValidator
    private String emailId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private String createdDate;

    public UserDTo() {
    }

    public UserDTo(String id, @Size(max = 255, message = "Invalid firstName") @NotBlank(message = "Invalid firstName") String firstName, @Size(max = 255, message = "Invalid lastName") @NotBlank(message = "Invalid lastName") String lastName, @Pattern(regexp = "(^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$)", message = "Please enter a valid birth date") String dob, @Pattern(regexp = "(^[0-9]{10})") @NotBlank String mobile, @NotBlank String emailId, String createdDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.mobile = mobile;
        this.emailId = emailId;
        this.createdDate = createdDate;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "UserServiceDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", mobile='" + mobile + '\'' +
                ", emailId='" + emailId + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
