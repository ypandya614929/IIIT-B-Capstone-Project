package bookmyconsultation.appointmentservice.repository;

import bookmyconsultation.appointmentservice.entity.PrescriptionServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrescriptionServiceRepository extends MongoRepository<PrescriptionServiceEntity, String>  {

}
