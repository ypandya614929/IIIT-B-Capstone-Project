package bookmyconsultation.ratingservice.repository;

import bookmyconsultation.ratingservice.entity.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<RatingEntity, String> {

    public List<RatingEntity> findByDoctorId(String doctorId);

}
