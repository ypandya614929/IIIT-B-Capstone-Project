package bookmyconsultation.userservice.repository;

import bookmyconsultation.userservice.entity.UserServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceRepository extends MongoRepository<UserServiceEntity, String> {
}