package bookmyconsultation.appointmentservice.dto;

import java.util.List;

public class ErrorDTO {

    private String errorCode;

    private String errorMessage ;

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

}
