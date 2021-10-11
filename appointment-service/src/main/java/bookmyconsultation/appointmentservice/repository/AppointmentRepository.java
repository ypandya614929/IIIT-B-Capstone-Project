package bookmyconsultation.appointmentservice.repository;

import bookmyconsultation.appointmentservice.entity.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<AppointmentEntity, String>  {

    List<AppointmentEntity> findByUserId(String userId);

}
