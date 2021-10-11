package bookmyconsultation.doctorservice.security;

import bookmyconsultation.doctorservice.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, String> {
}
