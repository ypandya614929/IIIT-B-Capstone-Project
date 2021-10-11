package bookmyconsultation.userservice.repository;

import bookmyconsultation.userservice.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


// user repository interface to communicate with database
@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
}