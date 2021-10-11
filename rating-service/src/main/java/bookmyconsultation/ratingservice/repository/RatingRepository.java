package bookmyconsultation.ratingservice.repository;

import bookmyconsultation.ratingservice.entity.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends MongoRepository<RatingEntity, String> {
}
