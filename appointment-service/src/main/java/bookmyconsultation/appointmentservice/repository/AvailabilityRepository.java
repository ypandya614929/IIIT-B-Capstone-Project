package bookmyconsultation.appointmentservice.repository;

import bookmyconsultation.appointmentservice.entity.AvailabilityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends MongoRepository<AvailabilityEntity, String> {

    List<AvailabilityEntity> findByDoctorIdAndIsBooked(String doctorId, boolean isBooked);

    List<AvailabilityEntity> findByDoctorIdAndAvailabilityDateAndTimeSlot(String doctorId, String availabilityDate, String timeSlot);

}