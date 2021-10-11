package bookmyconsultation.appointmentservice.repository;

import bookmyconsultation.appointmentservice.entity.AvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;


@Repository
public interface AvailabilityRepository extends JpaRepository<AvailabilityEntity, Integer> {

    List<AvailabilityEntity> findByDoctorIdAndIsBooked(String doctorId, boolean isBooked);

    List<AvailabilityEntity> findByDoctorIdAndAvailabilityDateAndTimeSlot(String doctorId, Date availabilityDate, String timeSlot);

}