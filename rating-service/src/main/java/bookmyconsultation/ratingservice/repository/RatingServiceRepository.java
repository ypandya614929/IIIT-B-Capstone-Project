package bookmyconsultation.ratingservice.repository;

import bookmyconsultation.ratingservice.entity.RatingServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingServiceRepository extends MongoRepository<RatingServiceEntity, String> {
}
