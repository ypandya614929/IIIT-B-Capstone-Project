package bookmyconsultation.doctorservice.dto;

import javax.validation.constraints.NotBlank;

public class UpdateDoctorDTO {

    @NotBlank
    private String approvedBy;

    @NotBlank
    private String approverComments;

    public UpdateDoctorDTO() {
    }

    public UpdateDoctorDTO(@NotBlank String approvedBy, @NotBlank String approverComments) {
        this.approvedBy = approvedBy;
        this.approverComments = approverComments;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }

    @Override
    public String toString() {
        return "UpdateDoctorDTO{" +
                "approvedBy='" + approvedBy + '\'' +
                ", approverComments='" + approverComments + '\'' +
                '}';
    }
}
