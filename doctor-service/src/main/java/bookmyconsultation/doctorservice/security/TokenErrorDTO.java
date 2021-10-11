package bookmyconsultation.doctorservice.security;

import java.util.List;

public class TokenErrorDTO {

    private String timestamp;

    private String status ;

    private String error;

    private String path;

    public TokenErrorDTO() {
    }

    public TokenErrorDTO(String timestamp, String status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "TokenErrorDTO{" +
                "timestamp='" + timestamp + '\'' +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
