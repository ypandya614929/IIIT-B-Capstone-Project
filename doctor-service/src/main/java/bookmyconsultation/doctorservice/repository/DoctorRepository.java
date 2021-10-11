package bookmyconsultation.doctorservice.repository;

import bookmyconsultation.doctorservice.entity.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {

    List<DoctorEntity> findByStatus(String Status);

    List<DoctorEntity> findBySpeciality(String speciality);

    List<DoctorEntity> findByStatusAndSpeciality(String status, String speciality);

}