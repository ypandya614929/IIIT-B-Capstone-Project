package bookmyconsultation.ratingservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id"})
public class RatingDTO {

    private String id;

    private String doctorId;

    private int rating;

    private String comments;

    public RatingDTO() {
    }

    public RatingDTO(String id, String doctorId, int rating, String comments) {
        this.id = id;
        this.doctorId = doctorId;
        this.rating = rating;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "id='" + id + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                '}';
    }
}
