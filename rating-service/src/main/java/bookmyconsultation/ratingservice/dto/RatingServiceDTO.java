package bookmyconsultation.ratingservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id"})
public class RatingServiceDTO {

    private String doctorId;
    private int rating;

    public RatingServiceDTO() {
    }

    public RatingServiceDTO(String doctorId, int rating) {
        this.doctorId = doctorId;
        this.rating = rating;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RatingServiceDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", rating=" + rating +
                '}';
    }

}
