package bookmyconsultation.appointmentservice.repository;

import bookmyconsultation.appointmentservice.entity.PrescriptionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrescriptionRepository extends MongoRepository<PrescriptionEntity, String>  {

}
