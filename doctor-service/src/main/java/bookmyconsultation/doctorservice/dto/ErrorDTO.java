package bookmyconsultation.doctorservice.dto;

import java.util.List;

// class is used for mapping errors
// for 400 and 404
public class ErrorDTO {

    private String errorCode;

    private String errorMessage ;

    private List<String> errorFields;

    public ErrorDTO() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(List<String> errorFields) {
        this.errorFields = errorFields;
    }
}
