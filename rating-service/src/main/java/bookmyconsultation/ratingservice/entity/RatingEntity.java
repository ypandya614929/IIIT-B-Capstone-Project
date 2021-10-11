package bookmyconsultation.ratingservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Document(collection = "rating")
public class RatingEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String doctorId;

    private int rating;

    public RatingEntity() {
    }

    public RatingEntity(String id, String doctorId, int rating) {
        this.id = id;
        this.doctorId = doctorId;
        this.rating = rating;
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

    @Override
    public String toString() {
        return "RatingServiceEntity{" +
                "id='" + id + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", rating=" + rating +
                '}';
    }

}
