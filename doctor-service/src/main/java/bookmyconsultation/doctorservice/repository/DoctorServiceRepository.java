package bookmyconsultation.doctorservice.repository;

import bookmyconsultation.doctorservice.entity.DoctorServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorServiceRepository extends MongoRepository<DoctorServiceEntity, String> {

    List<DoctorServiceEntity> findByStatus(String Status);

    List<DoctorServiceEntity> findBySpeciality(String speciality);

    List<DoctorServiceEntity> findByStatusAndSpeciality(String status, String speciality);

}