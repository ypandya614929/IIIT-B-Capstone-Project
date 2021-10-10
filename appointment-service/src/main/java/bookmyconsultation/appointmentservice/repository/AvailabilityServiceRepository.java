package bookmyconsultation.appointmentservice.repository;

import bookmyconsultation.appointmentservice.entity.AvailabilityServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityServiceRepository extends MongoRepository<AvailabilityServiceEntity, String> {

    List<AvailabilityServiceEntity> findByDoctorIdAndIsBooked(String doctorId, boolean isBooked);

}