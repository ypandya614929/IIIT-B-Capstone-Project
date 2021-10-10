package bookmyconsultation.appointmentservice.repository;

import bookmyconsultation.appointmentservice.entity.AppointmentServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentServiceRepository extends MongoRepository<AppointmentServiceEntity, String>  {

    List<AppointmentServiceEntity> findByUserId(String userId);

}
